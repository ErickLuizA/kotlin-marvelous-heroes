package com.deverick.marvelousheroes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersViewModel @ViewModelInject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    fun getCharacters() : Flow<PagingData<Character>> {
        return charactersRepository.getCharacters().cachedIn(viewModelScope)
    }

}