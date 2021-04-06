package com.deverick.marvelousheroes.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deverick.marvelousheroes.databinding.SearchFragmentBinding
import com.deverick.marvelousheroes.ui.adapters.CharactersAdapter
import com.deverick.marvelousheroes.utils.Resource
import com.deverick.marvelousheroes.viewmodels.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var binding: SearchFragmentBinding
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
        setupRecyclerView()

        charactersAdapter.setOnItemClickListener {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                    it
                )
            )
        }

        binding.searchView.setOnQueryTextListener(queryTextListener)

        viewModel.searchedCharacters.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Error -> {
                    hideLoading()
                    Snackbar.make(
                        view,
                        "Error occurred while searching character",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                is Resource.Success -> {
                    hideLoading()

                    charactersAdapter.differ.submitList(response.data?.data?.results)
                }
            }
        })
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                viewModel.searchCharacter(query)
            }

            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun setupNavigation() {
        val navController = findNavController()

        binding.toolbar.setupWithNavController(navController)
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