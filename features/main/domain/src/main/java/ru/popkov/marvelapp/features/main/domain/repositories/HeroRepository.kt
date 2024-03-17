package ru.popkov.marvelapp.features.main.domain.repositories

import ru.popkov.marvelapp.features.main.domain.model.HeroData

interface HeroRepository {
    suspend fun getHeroes(): HeroData

    suspend fun getHero(characterId: Int): HeroData
}
