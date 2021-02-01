package com.deverick.marvelousheroes.services.db

import androidx.room.Dao
import androidx.room.Query
import com.deverick.marvelousheroes.models.Character
import kotlinx.coroutines.flow.Flow

const val isFavorite = true
const val notFavorite = false

@Dao
interface CharacterDao {

    @Query("UPDATE characters SET favorite = $isFavorite WHERE id = :id")
    fun addFavorite(id: Int)

    @Query("UPDATE characters SET favorite = $notFavorite WHERE id = :id")
    fun deleteFavorite(id: Int)

    @Query("SELECT * FROM characters WHERE favorite = $isFavorite")
    fun getFavorites(): Flow<List<Character>>
}