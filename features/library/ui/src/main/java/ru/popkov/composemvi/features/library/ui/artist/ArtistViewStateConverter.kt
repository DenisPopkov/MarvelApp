package ru.popkov.composemvi.features.library.ui.artist

import ru.popkov.composemvi.features.library.domain.model.Artist
import ru.popkov.composemvi.features.library.domain.model.Song
import ru.popkov.composemvi.features.library.domain.usecase.FormatDurationUseCase
import javax.inject.Inject

internal class ArtistViewStateConverter @Inject constructor(
    private val formatDurationUseCase: FormatDurationUseCase,
) {

    fun Pair<Artist, List<Song>>.toViewState() =
        ArtistViewState(
            name = first.name,
            songs = second.map { it.toViewState() }
        )

    private fun Song.toViewState() =
        ArtistViewState.Song(
            name = title,
            duration = formatDurationUseCase(duration),
        )

}