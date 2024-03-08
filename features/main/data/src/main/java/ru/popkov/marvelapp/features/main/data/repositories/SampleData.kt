package ru.popkov.marvelapp.features.main.data.repositories

object SampleData {
    fun getHero(): List<HeroEntity> =
        listOf(
            HeroEntity(
                cardText = ru.popkov.marvelapp.features.main.ui.R.string.deadpool_hero,
                cardDesc = ru.popkov.marvelapp.features.main.ui.R.string.deadpool_desc,
                cardImageUrl = "https://iili.io/JMnAfIV.png",
            ),
            HeroEntity(
                cardText = ru.popkov.marvelapp.features.main.ui.R.string.iron_man_hero,
                cardDesc = ru.popkov.marvelapp.features.main.ui.R.string.iron_man_desc,
                cardImageUrl = "https://iili.io/JMnuDI2.png",
            ),
            HeroEntity(
                cardText = ru.popkov.marvelapp.features.main.ui.R.string.spider_man_hero,
                cardDesc = ru.popkov.marvelapp.features.main.ui.R.string.spider_man_desc,
                cardImageUrl = "https://iili.io/JMnuyB9.png",
            ),
        )
}
