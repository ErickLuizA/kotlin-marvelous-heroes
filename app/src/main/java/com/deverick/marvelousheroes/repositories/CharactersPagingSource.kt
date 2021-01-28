package com.deverick.marvelousheroes.repositories

import com.deverick.marvelousheroes.services.api.MarvelService
import androidx.paging.PagingSource
import com.deverick.marvelousheroes.models.Character
import retrofit2.HttpException
import java.io.IOException

private const val INITIAL_OFFSET = 0

class CharactersPagingSource(
    private val marvelService: MarvelService,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val offset = params.key ?: INITIAL_OFFSET

        return try {
            val response = marvelService.getCharacters(
                offset = offset
            )

            val data = response.body()?.data

            val results = data?.results

            if (results != null) {

                LoadResult.Page(
                    data = results,
                    nextKey = if (results.isEmpty()) null else offset + 20,
                    prevKey = if (offset == INITIAL_OFFSET) null else offset - 20,
                )
            } else {
                LoadResult.Error(Throwable("Empty"))
            }


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}










