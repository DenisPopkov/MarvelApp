package ru.popkov.marvelapp.features.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class StatusCode(
    val code: Int,
    val status: String,
) : Parcelable

@Parcelize
data class HeroData(
    @SerialName("status") val statusCode: StatusCode,
    @SerialName("data") val heroData: HeroResult
) : Parcelable

@Parcelize
data class HeroResult(
    @SerialName("results") val heroResult: HeroCard
) : Parcelable

@Parcelize
data class HeroThumbnail(
    @SerialName("path") val heroImageUrl: String,
    @SerialName("extension") val imageFileExtension: String,
) : Parcelable

@Parcelize
data class HeroCard(
    @SerialName("id") val heroId: Int,
    @SerialName("name") val heroName: String,
    @SerialName("description") val heroDescription: String,
    @SerialName("thumbnail") val heroThumbnail: HeroThumbnail,
) : Parcelable

