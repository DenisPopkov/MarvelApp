package ru.popkov.marvelapp.features.main.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HeroData(
    val code: Int,
    val status: String,
    val data: HeroResult
)

@Serializable
data class HeroResult(
    val results: List<HeroCard>
)

@Serializable
data class HeroCard(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail,
)

@Serializable
data class HeroThumbnail(
    val path: String,
    val extension: String,
)
