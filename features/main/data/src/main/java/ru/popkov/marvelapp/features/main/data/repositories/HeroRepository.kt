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
        if (heroes.code == 200) {
            heroDao.add(*heroes.toEntity().toTypedArray())
        }
        return heroDao.getHeroes().toDomain()
    }

    override suspend fun getHero(heroId: Int): Hero {
        val hero = marvelApi.getHero(heroId = heroId)
        if (hero.code == 200) {
            heroDao.add(*hero.toEntity().toTypedArray())
        }
        return heroDao.findHeroById(heroId).toDomain()
    }

    override suspend fun getLocalHeroes(): List<Hero> {
        return heroDao.getHeroes().toDomain()
    }

    override suspend fun getLocalHero(heroId: Int): Hero {
        return heroDao.findHeroById(heroId).toDomain()
    }
}
