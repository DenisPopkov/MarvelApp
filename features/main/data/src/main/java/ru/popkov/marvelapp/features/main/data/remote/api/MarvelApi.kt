package ru.popkov.marvelapp.features.main.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("public/characters")
    suspend fun getCharacters()

    @GET("public/characters/{character_id}")
    suspend fun getCharacter(
        @Path("character_id") characterId: Int
    )
}
