package com.deverick.marvelousheroes.services.api

import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.models.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("orderBy")
        orderBy: String = "-modified",
        @Query("limit")
        limit: Int = 20,
        @Query("offset")
        offset: Int = 0,
    ): Response<MarvelResponse<Character>>

    @GET("characters")
    suspend fun getCharacter(
        @Query("nameStartsWith")
        name: String
    ): Response<MarvelResponse<Character>>
}