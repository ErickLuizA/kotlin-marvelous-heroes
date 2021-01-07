package com.deverick.marvelousheroes.repositories

import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.models.MarvelResponse
import com.deverick.marvelousheroes.services.api.MarvelService
import retrofit2.Response
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val marvelService: MarvelService,
): CharactersRepository {
    override suspend fun getCharacters(): Response<MarvelResponse<Character>> {
        return marvelService.getCharacters()
    }
}