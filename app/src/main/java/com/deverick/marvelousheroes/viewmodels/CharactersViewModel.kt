package com.deverick.marvelousheroes.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.deverick.marvelousheroes.MarvelousHeroesApplication
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.models.MarvelResponse
import com.deverick.marvelousheroes.repositories.CharactersRepository
import com.deverick.marvelousheroes.utils.Resource
import com.deverick.marvelousheroes.utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel @ViewModelInject constructor(
    private val charactersRepository: CharactersRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _characters: MutableLiveData<Resource<MarvelResponse<Character>>> =
        MutableLiveData()

    private var _charactersResponse: MarvelResponse<Character>? = null
    var charactersPage = 0

    val characters: LiveData<Resource<MarvelResponse<Character>>>
        get() = _characters

    init {

        getCharacters()
    }


    fun getCharacters() = viewModelScope.launch {
        if (hasInternetConnection(getApplication<MarvelousHeroesApplication>().applicationContext)) {
            _characters.postValue(Resource.Loading())

            val response = charactersRepository.getCharacters(charactersPage)

            val result = handleGetCharacters(response)

            _characters.postValue(result)
        } else {
            _characters.postValue(Resource.Error("Network Failure"))
        }
    }

    private fun handleGetCharacters(response: Response<MarvelResponse<Character>>): Resource<MarvelResponse<Character>> {

        if (response.isSuccessful) {
            response.body()?.let { marvelResponse ->
                charactersPage += 20

                if (_charactersResponse == null) {
                    _charactersResponse = marvelResponse
                } else {
                    _charactersResponse!!.data.results.addAll(marvelResponse.data.results)
                }

                return Resource.Success(_charactersResponse ?: marvelResponse)
            }
        }

        return Resource.Error(response.message())
    }

}
