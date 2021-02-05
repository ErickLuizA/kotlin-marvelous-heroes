package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deverick.marvelousheroes.repositories.FavoritesRepository
import com.deverick.marvelousheroes.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _favorites: MutableLiveData<List<Character>> = MutableLiveData()
    val favorites: LiveData<List<Character>>
        get() = _favorites

    init {
        getFavorites()
    }

    private fun getFavorites() = viewModelScope.launch(Dispatchers.IO) {
        val response = favoritesRepository.getFavorites()

        _favorites.postValue(response)
    }

    fun addFavorite(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.addFavorite(character)
    }

    fun deleteFavorite(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.deleteFavorite(character)
    }
}