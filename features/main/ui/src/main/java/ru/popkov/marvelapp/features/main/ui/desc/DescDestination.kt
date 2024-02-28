package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

const val descRoute = "desc"
const val heroImageIdArg = "heroImageIdArg"
const val heroNameIdArg = "heroNameIdArg"
const val heroDescIdArg = "heroDescIdArg"

data class DescDestination(
    val heroImageId: Int,
    val heroNameId: Int,
    val heroDescId: Int
) :
    Destination {

    companion object : DestinationDefinition(
        route = "$descRoute?$heroImageIdArg={$heroImageIdArg}?$heroNameIdArg={$heroNameIdArg}?$heroDescIdArg={$heroDescIdArg}",
        args = listOf(
            navArgument(heroImageIdArg) {
                type = NavType.IntType
            },
            navArgument(heroNameIdArg) {
                type = NavType.IntType
            },
            navArgument(heroDescIdArg) {
                type = NavType.IntType
            }
        ),
    )

    data class Args(
        val heroImageId: Int?,
        val heroNameId: Int?,
        val heroDescId: Int?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            heroImageId = savedStateHandle.get<String>(heroImageIdArg)?.toIntOrNull(),
            heroNameId = savedStateHandle.get<String>(heroNameIdArg)?.toIntOrNull(),
            heroDescId = savedStateHandle.get<String>(heroDescIdArg)?.toIntOrNull(),
        )
    }

    override fun toString() =
        "$descRoute?$heroImageIdArg=$heroImageId?$heroNameIdArg=$heroNameId?$heroDescIdArg=$heroDescId"
}
