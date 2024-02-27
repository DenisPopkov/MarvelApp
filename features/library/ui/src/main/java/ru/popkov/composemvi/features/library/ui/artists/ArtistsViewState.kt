package ru.popkov.composemvi.features.library.ui.artists

import androidx.compose.runtime.Immutable
import ru.popkov.composemvi.features.library.ui.ItemViewState

@Immutable
internal data class ArtistsViewState(
    val items: List<ItemViewState<ArtistsViewAction>> = emptyList(),
)