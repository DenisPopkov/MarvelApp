package ru.popkov.marvelapp.features.main.data.repositories

import arrow.core.Either
import ru.popkov.marvelapp.features.main.data.local.daos.HeroDao
import ru.popkov.marvelapp.features.main.data.local.mappers.HeroMapper.toDomain
import ru.popkov.marvelapp.features.main.data.remote.api.MarvelApi
import ru.popkov.marvelapp.features.main.data.remote.mappers.HeroMapper.toEntity
import ru.popkov.marvelapp.features.main.domain.model.Hero
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import ru.popkov.marvelapp.features.main.domain.usecase.APICode
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorCode
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class HeroRepository @Inject constructor(
    private val marvelApi: MarvelApi,
    private val heroDao: HeroDao,
) : HeroRepository {
    override suspend fun getHeroes(): Either<ErrorCode, List<Hero>> {
        val heroes = Either.catch { marvelApi.getHeroes() }

        return when (heroes) {
            is Either.Right -> {
                heroDao.add(*heroes.value.toEntity().toTypedArray())
                Either.Right(heroDao.getHeroes().toDomain())
            }

            else -> Either.Left(ErrorCode(heroes.getOrNull()?.code ?: APICode.UNDEFINED.code))
        }
    }


    override suspend fun getHero(heroId: Int): Either<ErrorCode, Hero> {
        val hero = Either.catch { marvelApi.getHero(heroId) }

        return when (hero) {
            is Either.Right -> Either.Right(heroDao.findHeroById(heroId).toDomain())
            else -> Either.Left(ErrorCode(hero.getOrNull()?.code ?: APICode.UNDEFINED.code))
        }
    }

    override suspend fun getLocalHeroes(): List<Hero> {
        return heroDao.getHeroes().toDomain()
    }

    override suspend fun getLocalHero(heroId: Int): Hero {
        return heroDao.findHeroById(heroId).toDomain()
    }
}
