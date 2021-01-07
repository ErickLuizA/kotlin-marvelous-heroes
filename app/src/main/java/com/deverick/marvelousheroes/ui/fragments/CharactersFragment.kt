package com.deverick.marvelousheroes.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deverick.marvelousheroes.R
import com.deverick.marvelousheroes.databinding.FragmentCharactersBinding
import com.deverick.marvelousheroes.ui.adapters.CharactersAdapter
import com.deverick.marvelousheroes.utils.Resource
import com.deverick.marvelousheroes.viewmodels.CharactersViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
        setupRecyclerView()

        viewModel.characters.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.data?.let { res ->
                        charactersAdapter.differ.submitList(res.results)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "A error occurred $message")
                        Snackbar.make(
                            view,
                            getString(R.string.error_load_chars),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupNavigation() {
        val navController = findNavController()
        val configuration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.drawerView.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, configuration)
        binding.toolbar.title = null

    }

    private fun setupRecyclerView() {
        charactersAdapter = CharactersAdapter()

        binding.rvCharacters.apply {
            adapter = charactersAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}