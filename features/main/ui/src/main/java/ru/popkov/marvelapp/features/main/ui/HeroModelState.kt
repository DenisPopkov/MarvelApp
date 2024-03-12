package ru.popkov.marvelapp.features.main.ui

import ru.popkov.marvelapp.features.main.domain.model.HeroData

data class HeroModelState(
    val heroModel: List<HeroData> = emptyList(),
    val isLoading: Boolean = false,
)
