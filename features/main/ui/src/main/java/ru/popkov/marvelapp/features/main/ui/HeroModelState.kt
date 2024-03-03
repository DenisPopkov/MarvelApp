package ru.popkov.marvelapp.features.main.ui

import ru.popkov.marvelapp.features.main.domain.model.HeroCard

data class HeroModelState(
    val heroModel: List<HeroCard> = emptyList(),
    val isLoading: Boolean = false,
)
