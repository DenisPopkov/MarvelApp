package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

const val descRoute = "desc"
const val heroImageIdArg = "heroImageIdArg"

data class DescDestination(val heroImageId: Int) : Destination {

    companion object : DestinationDefinition(
        route = "$descRoute?$heroImageIdArg={$heroImageIdArg}",
        args = listOf(
            navArgument(heroImageIdArg) {
                type = NavType.IntType
            }
        ),
    )

    data class Args(
        val heroImageId: Int?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            heroImageId = savedStateHandle.get<Int>(heroImageIdArg),
        )
    }

    override fun toString() =
        "$descRoute?$heroImageIdArg=$heroImageIdArg"
}
