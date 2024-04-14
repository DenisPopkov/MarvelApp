package ru.popkov.marvelapp.features.main.ui.main

import ru.popkov.marvelapp.features.main.domain.model.Hero

data class HeroesModelState(
    val heroModel: List<Hero>? = null,
    val isLoading: Boolean = false,
)
