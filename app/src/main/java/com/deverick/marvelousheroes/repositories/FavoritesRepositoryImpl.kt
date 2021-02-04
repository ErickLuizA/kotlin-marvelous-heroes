package com.deverick.marvelousheroes.repositories

import androidx.lifecycle.LiveData
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.services.db.CharacterDao
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : FavoritesRepository {
    override suspend fun addFavorite(id: Int) {
        return characterDao.addFavorite(id)
    }

    override suspend fun deleteFavorite(id: Int) {
        return characterDao.deleteFavorite(id)
    }

    override suspend fun getFavorites(): List<Character> {
        return characterDao.getFavorites()
    }
}