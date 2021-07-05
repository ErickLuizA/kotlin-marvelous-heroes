package com.deverick.marvelousheroes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deverick.marvelousheroes.viewmodels.FavoritesViewModel
import com.deverick.marvelousheroes.databinding.FavoritesFragmentBinding
import com.deverick.marvelousheroes.ui.adapters.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FavoritesFragmentBinding
    private lateinit var favoritesAdapter: CharactersAdapter
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoritesFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        setupRecyclerView()

        favoritesAdapter.setOnItemClickListener {
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(it)
            )
        }

        viewModel.getFavorites().observe(viewLifecycleOwner, { characters ->
            favoritesAdapter.differ.submitList(characters)
        })
    }

    private fun setupToolbar() {
        val navController = findNavController()

        binding.detailsToolbar.setupWithNavController(navController)
        binding.detailsToolbar.title = null
    }

    private fun setupRecyclerView() {
        favoritesAdapter = CharactersAdapter()

        favoritesAdapter.setHasStableIds(true)

        binding.rvFavorites.apply {
            adapter = favoritesAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}