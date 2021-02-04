package com.deverick.marvelousheroes.services.db

import androidx.room.Dao
import androidx.room.Query
import com.deverick.marvelousheroes.models.Character

@Dao
interface CharacterDao {

    @Query("UPDATE characters SET favorite = 0 WHERE id = :id")
    fun addFavorite(id: Int)

    @Query("UPDATE characters SET favorite = 0 WHERE id = :id")
    fun deleteFavorite(id: Int)

    @Query("SELECT * FROM characters WHERE favorite = 1")
    fun getFavorites(): List<Character>
}