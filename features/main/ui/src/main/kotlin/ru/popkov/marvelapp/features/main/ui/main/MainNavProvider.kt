package ru.popkov.marvelapp.features.main.ui.main

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.NavProvider
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.marvelapp.features.main.nav.MainDestination
import ru.popkov.marvelapp.features.main.ui.desc.DescDestination
import ru.popkov.marvelapp.features.main.ui.desc.DescScreen
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class MainNavProvider @Inject constructor(
    private val navigator: Navigator,
) : NavProvider {

    override val item = NavProvider.Route(
        index = 0,
        route = MainDestination.route,
        isStart = true,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            composable(MainDestination.route) {
                MainScreen(
                    snackbarHostState = snackbarHostState,
                    onCardClick = { heroId ->
                        navigator.navigate(DescDestination(heroId)) {
                            popUpTo(MainDestination.route)
                        }
                    },
                )
            }
            composable(
                route = DescDestination.route,
                arguments = DescDestination.args
            ) {
                DescScreen(
                    snackbarHostState = snackbarHostState,
                    onBack = {
                        navigator.onBackClick()
                    },
                )
            }
        }
}
