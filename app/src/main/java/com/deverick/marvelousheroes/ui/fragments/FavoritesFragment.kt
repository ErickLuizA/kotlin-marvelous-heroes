package com.deverick.marvelousheroes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.deverick.marvelousheroes.viewmodels.FavoritesViewModel
import com.deverick.marvelousheroes.databinding.FavoritesFragmentBinding

class FavoritesFragment : Fragment() {

    private lateinit var binding: FavoritesFragmentBinding
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritesFragmentBinding.inflate(layoutInflater)

        return binding.root
    }
}