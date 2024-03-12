package ru.popkov.marvelapp.features.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class HeroData(
    val code: Int,
    val status: String,
    val data: HeroResult
) : Parcelable

@Serializable
@Parcelize
data class HeroResult(
    val results: List<HeroCard>
) : Parcelable

@Serializable
@Parcelize
data class HeroCard(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail,
) : Parcelable

@Serializable
@Parcelize
data class HeroThumbnail(
    val path: String,
    val extension: String,
) : Parcelable
