package com.deverick.marvelousheroes.services.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deverick.marvelousheroes.models.Character

@Database(
    entities = [Character::class],
    version = 1
)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun getCharactersDao(): CharacterDao

    companion object {
        @Volatile
        private var instance: CharactersDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CharactersDatabase::class.java,
                "characters_db.db"
            ).build()
    }
}