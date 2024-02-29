package ru.popkov.marvelapp.features.main.data.repositories

import ru.popkov.marvelapp.features.main.domain.model.HeroCard

object HeroMapper {

    fun HeroEntity.toDomain() =
        HeroCard(
            cardText = cardText,
            cardDesc = cardDesc,
            cardImageUrl = cardImageUrl,
        )
}
