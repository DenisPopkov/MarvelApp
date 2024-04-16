package ru.popkov.marvelapp.features.main.domain.repositories

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import ru.popkov.marvelapp.features.main.domain.model.Hero
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorCode

interface HeroRepository {
    suspend fun getHeroes(): Either<ErrorCode, List<Hero>>

    suspend fun getHero(heroId: Int): Either<ErrorCode, Hero>

    fun getLocalHeroes(): Flow<List<Hero>>

    fun getLocalHero(heroId: Int): Flow<Hero>
}
