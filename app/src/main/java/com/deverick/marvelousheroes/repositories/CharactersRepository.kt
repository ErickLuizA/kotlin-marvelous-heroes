package com.deverick.marvelousheroes.repositories

import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.models.MarvelResponse
import retrofit2.Response

interface CharactersRepository {
    suspend fun getCharacters(): Response<MarvelResponse<Character>>
}