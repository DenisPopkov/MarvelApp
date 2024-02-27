package ru.popkov.composemvi.features.library.domain.model

import kotlin.time.Duration

data class Song(
    val id: Long,
    val title: String,
    val duration: Duration,
)