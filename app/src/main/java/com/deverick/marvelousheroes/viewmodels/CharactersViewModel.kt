package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.models.MarvelResponse
import com.deverick.marvelousheroes.repositories.CharactersRepository
import com.deverick.marvelousheroes.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel @ViewModelInject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {
    private val _characters: MutableLiveData<Resource<MarvelResponse<Character>>> = MutableLiveData()
    val characters: LiveData<Resource<MarvelResponse<Character>>>
        get() = _characters

    init {
        getCharacters()
    }


    private fun getCharacters() = viewModelScope.launch {
        _characters.postValue(Resource.Loading())

        val response = charactersRepository.getCharacters()

        _characters.postValue(handleGetCharactersResponse(response))
    }

    private fun handleGetCharactersResponse(
        response: Response<MarvelResponse<Character>>
    ): Resource<MarvelResponse<Character>> {
        if(response.isSuccessful) {
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        }

        return Resource.Error(response.message())
    }
}