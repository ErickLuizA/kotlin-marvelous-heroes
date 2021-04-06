package com.deverick.marvelousheroes.repositories

import com.deverick.marvelousheroes.services.api.MarvelService
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val marvelService: MarvelService,
) : CharactersRepository {
    override suspend fun getCharacters(offset: Int) = marvelService.getCharacters(
        offset = offset
    )

    override suspend fun getCharacter(name: String) = marvelService.getCharacter(name)
}