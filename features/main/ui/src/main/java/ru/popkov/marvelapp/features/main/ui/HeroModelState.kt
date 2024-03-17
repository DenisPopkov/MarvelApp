package ru.popkov.marvelapp.features.main.ui

import ru.popkov.marvelapp.features.main.domain.model.HeroData

data class HeroModelState(
    val heroModel: HeroData? = null,
    val isLoading: Boolean = false,
)
