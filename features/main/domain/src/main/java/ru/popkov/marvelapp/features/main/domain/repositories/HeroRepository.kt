package ru.popkov.marvelapp.features.main.domain.repositories

import ru.popkov.marvelapp.features.main.domain.model.Hero

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>

    suspend fun getHero(heroId: Int): Hero

    suspend fun getLocalHeroes(): List<Hero>

    suspend fun getLocalHero(heroId: Int): Hero
}
