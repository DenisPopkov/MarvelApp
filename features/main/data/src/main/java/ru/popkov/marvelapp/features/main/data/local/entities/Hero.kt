package ru.popkov.marvelapp.features.main.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hero(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
)
