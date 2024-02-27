package ru.popkov.composemvi.features.library.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.composemvi.features.library.nav.LibraryDestination
import ru.popkov.composemvi.features.library.ui.artist.ArtistDestination
import ru.popkov.composemvi.features.library.ui.artist.ArtistScreen
import ru.popkov.composemvi.features.library.ui.artists.ArtistsDestination
import ru.popkov.composemvi.features.library.ui.artists.ArtistsScreen
import ru.popkov.composesample.features.library.nav.R
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class LibraryNavProvider @Inject constructor(
    private val navigator: Navigator,
) : NavProvider {

    override val navBarItem = NavProvider.BottomBarItem(
        index = 1,
        labelResId = R.string.library_bottomnav_label,
        icon = Icons.Filled.Home,
        route = LibraryDestination.route,
        isStart = false,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            navigation(
                startDestination = ArtistsDestination.route,
                route = LibraryDestination.route,
                arguments = LibraryDestination.args,
            ) {
                composable(
                    route = ArtistsDestination.route,
                ) {
                    ArtistsScreen(
                        snackbarHostState = snackbarHostState,
                        onArtistSelect = { id -> navigator.navigate(ArtistDestination(id)) },
                    )
                }
                composable(
                    route = ArtistDestination.route,
                    arguments = ArtistDestination.args,
                ) {
                    ArtistScreen()
                }
            }
        }

}