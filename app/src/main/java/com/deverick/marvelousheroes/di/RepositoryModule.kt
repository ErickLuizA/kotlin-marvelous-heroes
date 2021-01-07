package com.deverick.marvelousheroes.di

import com.deverick.marvelousheroes.repositories.CharactersRepositoryImpl
import com.deverick.marvelousheroes.repositories.CharactersRepository
import com.deverick.marvelousheroes.services.api.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharactersRepository(
        marvelService: MarvelService
    ): CharactersRepository {
        return CharactersRepositoryImpl(marvelService = marvelService)
    }
}