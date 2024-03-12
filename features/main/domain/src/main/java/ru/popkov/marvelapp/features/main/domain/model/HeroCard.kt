package ru.popkov.marvelapp.features.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class HeroData(
    val code: Int,
    val status: String,
    @SerialName("data") val data: HeroResult
) : Parcelable

@Serializable
@Parcelize
data class HeroResult(
    @SerialName("results") val results: List<HeroCard>
) : Parcelable

@Serializable
@Parcelize
data class HeroCard(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("thumbnail") val thumbnail: HeroThumbnail,
) : Parcelable

@Serializable
@Parcelize
data class HeroThumbnail(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String,
) : Parcelable
