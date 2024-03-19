package ru.popkov.marvelapp.features.main.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.popkov.marvelapp.features.main.data.local.entities.Hero

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(hero: Hero)
}
