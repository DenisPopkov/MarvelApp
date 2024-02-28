package ru.popkov.marvelapp.features.main.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.popkov.marvelapp.features.main.domain.model.HeroCard

interface HeroRepository {

    suspend fun getHero(): Flow<List<HeroCard>>
}
