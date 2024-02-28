package ru.popkov.marvelapp.features.main.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun getHero(): Flow<List<HeroCard>> =
        SampleData.getHero().map { hero -> hero.map { it.toDomain() } }
}
