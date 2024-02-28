package ru.popkov.marvelapp.features.main.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

private const val MAIN_ROUTE = "main"
private const val HERO_IMAGE_ID_ARG = "heroImageIdArg"

data class MainDestination(val heroImageId: Int) : Destination {

    companion object : DestinationDefinition(
        route = "$MAIN_ROUTE?$HERO_IMAGE_ID_ARG={$HERO_IMAGE_ID_ARG}",
        args = listOf(
            navArgument(HERO_IMAGE_ID_ARG) {
                type = NavType.IntType
                nullable = true
            }
        ),
    )

    data class Args(
        val heroImageId: Int?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            heroImageId = savedStateHandle.get<Int>(HERO_IMAGE_ID_ARG),
        )
    }

    override fun toString() =
        "$MAIN_ROUTE?$HERO_IMAGE_ID_ARG=$HERO_IMAGE_ID_ARG"
}
