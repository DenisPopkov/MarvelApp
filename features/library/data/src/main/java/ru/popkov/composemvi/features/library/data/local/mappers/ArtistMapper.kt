package ru.popkov.composemvi.features.library.data.local.mappers

import ru.popkov.composemvi.features.library.data.local.entities.Artist as ArtistEntity
import ru.popkov.composemvi.features.library.data.local.entities.ArtistWithSongs as ArtistWithSongsEntity
import ru.popkov.composemvi.features.library.data.local.entities.ArtistWithSongCount as ArtistWithSongCountEntity
import ru.popkov.composemvi.features.library.data.local.mappers.SongMapper.toDomain
import ru.popkov.composemvi.features.library.domain.model.Artist
import ru.popkov.composemvi.features.library.domain.model.Song

object ArtistMapper {

    fun ArtistEntity.toDomain() =
        Artist(
            id = id,
            name = name,
        )

    fun ArtistWithSongCountEntity.toDomain(): Pair<Artist, Int> =
        artist.toDomain() to songCount

    fun ArtistWithSongsEntity.toDomain(): Pair<Artist, List<Song>> =
        artist.toDomain() to songs.map { it.toDomain() }

}