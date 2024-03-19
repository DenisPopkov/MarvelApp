package ru.popkov.marvelapp.features.main.data.repositories

import ru.popkov.marvelapp.features.main.data.local.daos.HeroDao
import ru.popkov.marvelapp.features.main.data.local.mappers.HeroMapper.toDomain
import ru.popkov.marvelapp.features.main.data.remote.api.MarvelApi
import ru.popkov.marvelapp.features.main.data.remote.mappers.HeroMapper.toEntity
import ru.popkov.marvelapp.features.main.domain.model.Hero
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class HeroRepository @Inject constructor(
    private val marvelApi: MarvelApi,
    private val heroDao: HeroDao,
) : HeroRepository {
    override suspend fun getHeroes(): List<Hero> {
        val heroes = marvelApi.getHeroes()
        heroDao.add(*heroes.toEntity().toTypedArray())
        return heroDao.getHeroes().toDomain()
    }

    override suspend fun getHero(
        characterId: Int,
    ): Hero {
        val hero = marvelApi.getHero(characterId = characterId)
        heroDao.add(*hero.toEntity().toTypedArray())
        return heroDao.findHeroById(characterId).toDomain()
    }
}
