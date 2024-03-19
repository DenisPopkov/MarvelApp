package ru.popkov.marvelapp.features.main.domain.repositories

import ru.popkov.marvelapp.features.main.domain.model.Hero

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>

    suspend fun getHero(characterId: Int): Hero
}
