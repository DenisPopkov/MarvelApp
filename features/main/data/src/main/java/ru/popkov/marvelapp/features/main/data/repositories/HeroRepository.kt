package ru.popkov.marvelapp.features.main.data.repositories

import ru.popkov.marvelapp.features.main.data.local.daos.HeroDao
import ru.popkov.marvelapp.features.main.data.remote.api.MarvelApi
import ru.popkov.marvelapp.features.main.domain.model.HeroData
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
    override suspend fun getHeroes(): HeroData {
        return marvelApi.getHeroes()
    }

    override suspend fun getHero(
        characterId: Int,
    ): HeroData {
        return marvelApi.getHero(
            characterId = characterId,
        )
    }
}
