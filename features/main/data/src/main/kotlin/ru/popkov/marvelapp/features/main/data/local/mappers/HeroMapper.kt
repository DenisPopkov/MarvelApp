package ru.popkov.marvelapp.features.main.data.local.mappers

import ru.popkov.marvelapp.features.main.domain.model.Hero
import ru.popkov.marvelapp.features.main.data.local.entities.Hero as HeroEntity

object HeroMapper {

    fun HeroEntity.toDomain() =
        Hero(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl,
        )

    fun List<HeroEntity>.toDomain() = map { it.toDomain() }
}
