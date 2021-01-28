package com.deverick.marvelousheroes.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deverick.marvelousheroes.R
import com.deverick.marvelousheroes.databinding.FragmentCharactersBinding
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.ui.adapters.CharactersAdapter
import com.deverick.marvelousheroes.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
        setupRecyclerView()

        charactersAdapter.setOnItemClickListener {
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(
                    it
                )
            )
        }

        getCharacters()
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

    private fun getCharacters() {
        lifecycleScope.launch {
            viewModel.getCharacters().collectLatest { pagingData ->
                charactersAdapter.submitData(pagingData)
            }
        }
    }
}