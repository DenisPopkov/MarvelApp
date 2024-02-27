package ru.popkov.composemvi.features.library.data.remote.dtos

data class Artist(
    val name: String,
    val songs: List<Song>,
)