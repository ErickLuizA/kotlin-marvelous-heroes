package com.deverick.marvelousheroes.repositories

import androidx.lifecycle.LiveData
import com.deverick.marvelousheroes.models.Character

interface FavoritesRepository {
    suspend fun addFavorite(id: Int)

    suspend fun deleteFavorite(id: Int)

    suspend fun getFavorites(): List<Character>
}