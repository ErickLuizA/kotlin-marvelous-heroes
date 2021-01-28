package com.deverick.marvelousheroes.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.services.api.MarvelService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val marvelService: MarvelService,
) : CharactersRepository {
    override fun getCharacters(
    ): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CharactersPagingSource(marvelService) }
    ).flow
}