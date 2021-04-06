package com.deverick.marvelousheroes.repositories

import androidx.lifecycle.LiveData
import com.deverick.marvelousheroes.models.Character

interface FavoritesRepository {
    suspend fun addFavorite(character: Character)

    suspend fun deleteFavorite(character: Character)

    fun getFavorites(): LiveData<List<Character>>
}