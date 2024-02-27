package ru.popkov.composemvi.features.library.ui.artists

import ru.popkov.composemvi.features.library.domain.model.Artist
import ru.popkov.composemvi.features.library.ui.ItemViewState

internal object ArtistsViewStateConverter {

    fun List<Pair<Artist, Int>>.toViewState() =
        map { it.toViewState() }

    private fun Pair<Artist, Int>.toViewState() =
        ItemViewState<ArtistsViewAction>(
            label = first.name,
            description = "$second songs",
            onClickAction = ArtistsViewAction.ArtistClick(first.id),
        )

}