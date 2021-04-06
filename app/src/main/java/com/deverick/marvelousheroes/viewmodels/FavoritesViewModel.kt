package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.deverick.marvelousheroes.repositories.FavoritesRepository

class FavoritesViewModel @ViewModelInject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    fun getFavorites() = favoritesRepository.getFavorites()

}