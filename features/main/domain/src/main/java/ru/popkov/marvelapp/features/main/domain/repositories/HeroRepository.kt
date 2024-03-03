package ru.popkov.marvelapp.features.main.domain.repositories

import ru.popkov.marvelapp.features.main.domain.model.HeroCard

interface HeroRepository {

    suspend fun getHero(): List<HeroCard>
}
