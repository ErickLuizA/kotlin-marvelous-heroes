package com.deverick.marvelousheroes.repositories

import androidx.lifecycle.LiveData
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.services.db.CharacterDao
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : FavoritesRepository {
    override suspend fun addFavorite(character: Character) {
        return characterDao.addFavorite(character)
    }

    override suspend fun deleteFavorite(character: Character) {
        return characterDao.deleteFavorite(character)
    }

    override suspend fun getFavorites(): List<Character> {
        return characterDao.getFavorites()
    }
}