package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.repositories.FavoritesRepository
import com.deverick.marvelousheroes.utils.Resource

class FavoritesViewModel @ViewModelInject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _characters: MutableLiveData<Resource<List<Character>>> = MutableLiveData()
    val characters: LiveData<Resource<List<Character>>>
        get() = _characters

    init {
        getFavorites()
    }

    private fun getFavorites() {
        _characters.postValue(Resource.Loading())

        val response = favoritesRepository.getFavorites()

        _characters.postValue(Resource.Success(response))
    }

    fun addFavorite(id: Int) {
        favoritesRepository.addFavorite(id)
    }

    fun deleteFavorite(id: Int) {
        favoritesRepository.deleteFavorite(id)
    }
}