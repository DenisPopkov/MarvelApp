package ru.popkov.marvelapp.features.main.data.remote.dtos

data class Hero(
    val code: Int,
    val status: String,
    val data: HeroResult
)

data class HeroResult(
    val results: List<HeroCard>
)

data class HeroCard(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail,
)

data class HeroThumbnail(
    val path: String,
    val extension: String,
)
