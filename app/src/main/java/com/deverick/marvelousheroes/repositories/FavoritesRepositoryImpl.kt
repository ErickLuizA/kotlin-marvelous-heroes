package com.deverick.marvelousheroes.repositories

import androidx.lifecycle.LiveData
import com.deverick.marvelousheroes.models.Character
import com.deverick.marvelousheroes.services.db.CharacterDao
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : FavoritesRepository {
    override fun addFavorite(id: Int) {
        return characterDao.addFavorite(id)
    }

    override fun deleteFavorite(id: Int) {
        return characterDao.deleteFavorite(id)
    }

    override fun getFavorites(): LiveData<List<Character>> {
        return characterDao.getFavorites()
    }
}