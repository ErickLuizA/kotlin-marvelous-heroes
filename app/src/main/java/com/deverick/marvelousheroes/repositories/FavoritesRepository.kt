package com.deverick.marvelousheroes.repositories

import com.deverick.marvelousheroes.models.Character

interface FavoritesRepository {
    suspend fun addFavorite(character: Character)

    suspend fun deleteFavorite(character: Character)

    suspend fun getFavorites(): List<Character>
}