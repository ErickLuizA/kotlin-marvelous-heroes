package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.repositories.FavoritesRepository
import com.deverick.marvelousheroes.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel @ViewModelInject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _characters: MutableLiveData<Resource<List<Character>>> = MutableLiveData()
    val characters: LiveData<Resource<List<Character>>>
        get() = _characters

    init {
        getFavorites()
    }

    private fun getFavorites() = viewModelScope.launch(Dispatchers.IO) {
        _characters.postValue(Resource.Loading())

        val response = favoritesRepository.getFavorites()

        _characters.postValue(Resource.Success(response))
    }

    fun addFavorite(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.addFavorite(id)
    }

    fun deleteFavorite(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        favoritesRepository.deleteFavorite(id)
    }
}