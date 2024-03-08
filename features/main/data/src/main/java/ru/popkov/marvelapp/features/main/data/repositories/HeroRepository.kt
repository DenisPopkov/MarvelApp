package ru.popkov.marvelapp.features.main.data.repositories

import ru.popkov.marvelapp.features.main.data.repositories.HeroMapper.toDomain
import ru.popkov.marvelapp.features.main.domain.model.HeroCard
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class HeroRepository @Inject constructor() :
    HeroRepository {

    override suspend fun getHero(): List<HeroCard> =
        SampleData.getHero().map { hero -> hero.toDomain() }
}
