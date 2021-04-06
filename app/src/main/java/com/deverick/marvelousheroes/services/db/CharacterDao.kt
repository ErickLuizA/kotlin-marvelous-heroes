package com.deverick.marvelousheroes.services.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.deverick.marvelousheroes.models.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(character: Character)

    @Delete
    fun deleteFavorite(character: Character)

    @Query("SELECT * FROM characters")
    fun getFavorites(): LiveData<List<Character>>
}