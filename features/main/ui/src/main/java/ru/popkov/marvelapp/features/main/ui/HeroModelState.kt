package ru.popkov.marvelapp.features.main.ui

import ru.popkov.marvelapp.features.main.domain.model.Hero

data class HeroModelState(
    val heroModel: List<Hero>? = null,
    val isLoading: Boolean = false,
)
