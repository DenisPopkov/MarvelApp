package ru.popkov.marvelapp.features.main.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.popkov.marvelapp.features.main.data.remote.api.MarvelApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("developer.marvel.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    fun marvelApi(retrofit: Retrofit): MarvelApi =
        retrofit.create(MarvelApi::class.java)
}