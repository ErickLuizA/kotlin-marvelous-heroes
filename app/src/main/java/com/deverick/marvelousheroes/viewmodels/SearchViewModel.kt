package com.deverick.marvelousheroes.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.deverick.marvelousheroes.MarvelousHeroesApplication
import com.deverick.marvelousheroes.models.MarvelResponse
import com.deverick.marvelousheroes.repositories.CharactersRepository
import com.deverick.marvelousheroes.utils.Resource
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel @ViewModelInject constructor(
    private val charactersRepository: CharactersRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _searchedCharacters = MutableLiveData<Resource<MarvelResponse<Character>>>()
    val searchedCharacters: LiveData<Resource<MarvelResponse<Character>>>
        get() = _searchedCharacters

    fun searchCharacter(name: String) = viewModelScope.launch {
        if (hasInternetConnection(getApplication<MarvelousHeroesApplication>().applicationContext)) {
            _searchedCharacters.postValue(Resource.Loading())

            val response = charactersRepository.getCharacter(name)

            _searchedCharacters.postValue(handleSearchCharacter(response))
        } else {
            _searchedCharacters.postValue(Resource.Error("Network Failure"))

        }
    }

    private fun handleSearchCharacter(response: Response<MarvelResponse<Character>>): Resource<MarvelResponse<Character>> {
        if (response.isSuccessful) {
            response.body()?.let { marvelResponse ->
                return Resource.Success(marvelResponse)
            }
        }

        return Resource.Error(response.message())
    }
}