package ru.popkov.marvelapp.features.main.data.remote.api

import retrofit2.http.GET

interface MarvelApi {

    @GET("public/characters")
    suspend fun getCharacters()

}