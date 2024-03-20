package ru.popkov.marvelapp.features.main.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.popkov.marvelapp.features.main.data.remote.dtos.Hero

interface MarvelApi {

    @GET("characters")
    suspend fun getHeroes(
        @Query("limit") limit: Long = 3,
        @Query("offset") offset: Long = 0
    ): Hero

    @GET("characters/{characterId}")
    suspend fun getHero(
        @Path("characterId") heroId: Int,
    ): Hero
}
