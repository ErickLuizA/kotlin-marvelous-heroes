package com.deverick.marvelousheroes.repositories

import androidx.lifecycle.LiveData
import com.deverick.marvelousheroes.models.Character

interface FavoritesRepository {
    fun addFavorite(id: Int)

    fun deleteFavorite(id: Int)

    fun getFavorites(): List<Character>
}