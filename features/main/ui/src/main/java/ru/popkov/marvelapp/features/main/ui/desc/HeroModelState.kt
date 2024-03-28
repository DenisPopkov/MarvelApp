package ru.popkov.marvelapp.features.main.ui.desc

import ru.popkov.marvelapp.features.main.domain.model.Hero

data class HeroModelState(
    val heroModel: Hero? = null,
    val isLoading: Boolean = false,
)
