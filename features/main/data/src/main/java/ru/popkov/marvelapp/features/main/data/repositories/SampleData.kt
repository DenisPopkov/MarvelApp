package ru.popkov.marvelapp.features.main.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object SampleData {
    fun getHero(): Flow<List<HeroEntity>> = flowOf(
        listOf(
            HeroEntity(
                cardText = ru.popkov.marvelapp.features.main.ui.R.string.deadpool_hero,
                cardImage = ru.popkov.marvelapp.features.main.ui.R.drawable.ic_deadpool_card_image
            ),
            HeroEntity(
                cardText = ru.popkov.marvelapp.features.main.ui.R.string.iron_man_hero,
                cardImage = ru.popkov.marvelapp.features.main.ui.R.drawable.ic_iron_man
            ),
            HeroEntity(
                cardText = ru.popkov.marvelapp.features.main.ui.R.string.spider_man_hero,
                cardImage = ru.popkov.marvelapp.features.main.ui.R.drawable.ic_spider_card_image
            ),
        )
    )
}
