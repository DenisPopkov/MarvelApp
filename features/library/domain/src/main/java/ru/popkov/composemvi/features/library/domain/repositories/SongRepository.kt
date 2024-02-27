package ru.popkov.composemvi.features.library.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.popkov.composemvi.features.library.domain.model.Artist
import ru.popkov.composemvi.features.library.domain.model.Song

interface SongRepository {

    fun getArtists(): Flow<List<Pair<Artist, Int>>>

    suspend fun getArtist(id: Long): Pair<Artist, List<Song>>?

    suspend fun findArtistIdByName(name: String): Long?

    suspend fun download(): Int

}