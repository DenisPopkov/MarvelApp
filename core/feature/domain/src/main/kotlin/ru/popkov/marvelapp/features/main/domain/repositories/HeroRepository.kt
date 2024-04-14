package ru.popkov.marvelapp.features.main.domain.repositories

import arrow.core.Either
import ru.popkov.marvelapp.features.main.domain.model.Hero
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorCode

interface HeroRepository {
    suspend fun getHeroes(): Either<ErrorCode, List<Hero>>

    suspend fun getHero(heroId: Int): Either<ErrorCode, Hero>

    suspend fun getLocalHeroes(): List<Hero>

    suspend fun getLocalHero(heroId: Int): Hero
}
