package ru.popkov.marvelapp.features.main.data.local.mappers

import ru.popkov.marvelapp.features.main.domain.model.HeroCard
import ru.popkov.marvelapp.features.main.domain.model.HeroThumbnail
import ru.popkov.marvelapp.features.main.data.local.entities.Hero as HeroEntity

object HeroMapper {

    fun HeroEntity.toDomain() =
        HeroCard(
            id = id,
            name = name,
            description = description,
            thumbnail = HeroThumbnail("", ""),
        )
}
