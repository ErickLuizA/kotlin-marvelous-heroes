package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deverick.marvelousheroes.repositories.FavoritesRepository
import com.deverick.marvelousheroes.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    fun getFavorites() = favoritesRepository.getFavorites()

    fun addFavorite(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.addFavorite(character)
    }

    fun deleteFavorite(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.deleteFavorite(character)
    }
}