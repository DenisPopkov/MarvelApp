package ru.popkov.marvelapp.features.main.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.popkov.marvelapp.features.main.data.local.entities.Hero

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(vararg hero: Hero)

    @Query("SELECT * FROM hero")
    suspend fun getHeroes(): List<Hero>

    @Transaction
    @Query("SELECT * FROM hero WHERE id = :id")
    suspend fun findHeroById(id: Int): Hero
}
