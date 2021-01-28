package com.deverick.marvelousheroes.repositories

import androidx.paging.PagingData
import com.deverick.marvelousheroes.models.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}