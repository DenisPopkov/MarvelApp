package ru.popkov.marvelapp.features.main.data.remote.mappers

import ru.popkov.marvelapp.features.main.data.local.entities.Hero as HeroEntity
import ru.popkov.marvelapp.features.main.data.remote.dtos.Hero as HeroDto

object HeroMapper {

    fun HeroDto.toEntity() =
        data.results.map {
            HeroEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = convertUrl(
                    url = it.thumbnail.path,
                    extension = it.thumbnail.extension,
                )
            )
        }

    /*
    API returned thumbnail url like: http://url and extension like: .jpg
    Coil can't work with http, so we need to convert it to https and also
    concatenate extension for url to get access to image
     */
    private fun convertUrl(url: String, extension: String): String =
        "${url.replace("http", "https")}.$extension"
}
