package ru.popkov.marvelapp.features.main.data.remote.mappers

import ru.popkov.marvelapp.features.main.data.local.entities.Hero as HeroEntity
import ru.popkov.marvelapp.features.main.data.remote.dtos.Hero as HeroDto

object HeroMapper {

    fun HeroDto.toEntity() =
        HeroEntity(
            id = 0,
            name = name,
            description = description,
            thumbnail = thumbnail,
        )
}
