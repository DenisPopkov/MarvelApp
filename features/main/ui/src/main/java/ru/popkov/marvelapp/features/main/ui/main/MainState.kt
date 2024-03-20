package ru.popkov.marvelapp.features.main.ui.main

import androidx.compose.runtime.Immutable
import ru.popkov.marvelapp.features.main.domain.model.Hero

@Immutable
internal data class MainState(
    val heroModel: List<Hero>? = null,
    val isLoading: Boolean = false,
)
