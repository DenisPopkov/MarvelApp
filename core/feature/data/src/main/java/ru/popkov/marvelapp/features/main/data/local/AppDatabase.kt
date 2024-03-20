package ru.popkov.marvelapp.features.main.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.popkov.marvelapp.features.main.data.local.daos.HeroDao
import ru.popkov.marvelapp.features.main.data.local.entities.Hero

@Database(entities = [Hero::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
}
