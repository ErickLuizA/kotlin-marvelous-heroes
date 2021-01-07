package com.deverick.marvelousheroes.di

import com.deverick.marvelousheroes.services.api.MarvelService
import com.deverick.marvelousheroes.services.api.MarvelServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMarvelService(): MarvelService {
        return MarvelServiceGenerator().createService(MarvelService::class.java)
    }
}