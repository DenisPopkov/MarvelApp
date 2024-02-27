package ru.popkov.composemvi.features.library.data.remote.mappers

import ru.popkov.composemvi.features.library.data.local.entities.Artist as ArtistEntity
import ru.popkov.composemvi.features.library.data.remote.dtos.Artist as ArtistDto

object ArtistMapper {

    fun ArtistDto.toEntity() =
        ArtistEntity(
            id = 0,
            name = name,
        )

}