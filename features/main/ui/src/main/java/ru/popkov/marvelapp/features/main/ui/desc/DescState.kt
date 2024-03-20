package ru.popkov.marvelapp.features.main.ui.desc

import androidx.compose.runtime.Immutable
import ru.popkov.marvelapp.features.main.domain.model.Hero

@Immutable
internal data class DescState(
    val heroModel: Hero? = null,
    val isLoading: Boolean = false,
)
