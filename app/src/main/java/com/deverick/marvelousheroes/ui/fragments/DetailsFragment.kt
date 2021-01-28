package com.deverick.marvelousheroes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.deverick.marvelousheroes.viewmodels.DetailsViewModel
import com.deverick.marvelousheroes.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()

        binding.characterName.text = args.character.name
    }

    private fun setupToolbar() {
        val navController = findNavController()

        binding.detailsToolbar.setupWithNavController(navController)
        binding.detailsToolbar.title = null
    }
}