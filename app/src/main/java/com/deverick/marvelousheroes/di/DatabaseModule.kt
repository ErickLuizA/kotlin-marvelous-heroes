package com.deverick.marvelousheroes.di

import android.content.Context
import com.deverick.marvelousheroes.services.db.CharacterDao
import com.deverick.marvelousheroes.services.db.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCharactersDatabase(@ApplicationContext context: Context): CharactersDatabase {
        return CharactersDatabase.invoke(context)
    }

    @Provides
    fun provideCharacterDao(charactersDatabase: CharactersDatabase): CharacterDao {
        return charactersDatabase.getCharactersDao()
    }
}