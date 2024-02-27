package ru.popkov.composemvi.features.library.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.popkov.composemvi.features.library.data.local.daos.ArtistDao
import ru.popkov.composemvi.features.library.data.local.daos.SongDao
import ru.popkov.composemvi.features.library.data.local.entities.Artist
import ru.popkov.composemvi.features.library.data.local.entities.Song

@Database(entities = [Song::class, Artist::class], version = 4)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    abstract fun artistDao(): ArtistDao

}
