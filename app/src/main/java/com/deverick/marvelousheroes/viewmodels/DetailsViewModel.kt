package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.deverick.marvelousheroes.repositories.FavoritesRepository
import com.deverick.marvelousheroes.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private var _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    fun getFavorites(character: Character) = viewModelScope.launch {
        favoritesRepository.getFavorites().asFlow().collect { characters ->
            characters.forEach {
                if (it.id == character.id) {
                    _isFavorite.postValue(true)
                }
            }
        }
    }

    fun toggleFavorite(character: Character) {
        if (_isFavorite.value == false) {
            addFavorite(character)
        } else {
            deleteFavorite(character)
        }
    }

    private fun addFavorite(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.addFavorite(character)

        _isFavorite.postValue(true)
    }

    private fun deleteFavorite(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.deleteFavorite(character)

        _isFavorite.postValue(false)
    }
}