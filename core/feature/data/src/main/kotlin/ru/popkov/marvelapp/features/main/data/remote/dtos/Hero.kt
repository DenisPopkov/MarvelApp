package ru.popkov.marvelapp.features.main.data.remote.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hero(
    val code: Int,
    val status: String,
    val data: HeroResult
)

@JsonClass(generateAdapter = true)
data class HeroResult(
    val results: List<HeroCard>
)

@JsonClass(generateAdapter = true)
data class HeroCard(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail,
)

@JsonClass(generateAdapter = true)
data class HeroThumbnail(
    val path: String,
    val extension: String,
)
