package com.deverick.marvelousheroes.repositories

import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.models.MarvelResponse
import retrofit2.Response

interface CharactersRepository {
    suspend fun getCharacters(offset: Int): Response<MarvelResponse<Character>>

    suspend fun getCharacter(name: String): Response<MarvelResponse<Character>>
}