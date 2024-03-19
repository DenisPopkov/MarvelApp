package ru.popkov.marvelapp.features.main.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hero(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
)
