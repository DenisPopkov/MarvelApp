package ru.popkov.marvelapp.features.main.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.popkov.marvelapp.features.main.data.remote.api.MarvelApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLogging())
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private fun createLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .build()

    @Provides
    fun marvelApi(retrofit: Retrofit): MarvelApi =
        retrofit.create(MarvelApi::class.java)
}
