package ru.popkov.marvelapp.features.main.domain.repositories

import ru.popkov.marvelapp.features.main.domain.model.Hero

@JvmInline
value class Error(val code: Int)

interface HeroRepository {
    suspend fun getHeroes(): Pair<Error?, List<Hero>>

    suspend fun getHero(heroId: Int): Pair<Error?, Hero>

    suspend fun getLocalHeroes(): List<Hero>

    suspend fun getLocalHero(heroId: Int): Hero
}
