package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

const val descRoute = "desc"
const val heroIdArg = "heroIdArg"

data class DescDestination(
    val heroId: String,
) : Destination {

    companion object : DestinationDefinition(
        route = "$descRoute?$heroIdArg={$heroIdArg}",
        args = listOf(
            navArgument(heroIdArg) {
                type = NavType.StringType
            },
        ),
    )

    data class Args(
        val heroId: String?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            heroId = savedStateHandle.get<String>(heroIdArg),
        )
    }

    override fun toString() =
        "$descRoute?$heroIdArg={$heroId}"
}
