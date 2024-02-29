package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

const val descRoute = "desc"
const val heroImageUrlArg = "heroImageUrlArg"
const val heroNameIdArg = "heroNameIdArg"
const val heroDescIdArg = "heroDescIdArg"

data class DescDestination(
    val heroImageUrl: String,
    val heroNameId: Int,
    val heroDescId: Int
) :
    Destination {

    companion object : DestinationDefinition(
        route = "$descRoute?$heroImageUrlArg={$heroImageUrlArg}?$heroNameIdArg={$heroNameIdArg}?$heroDescIdArg={$heroDescIdArg}",
        args = listOf(
            navArgument(heroImageUrlArg) {
                type = NavType.StringType
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
        val heroImageUrl: String?,
        val heroNameId: Int?,
        val heroDescId: Int?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            heroImageUrl = savedStateHandle.get<String>(heroImageUrlArg),
            heroNameId = savedStateHandle.get<String>(heroNameIdArg)?.toIntOrNull(),
            heroDescId = savedStateHandle.get<String>(heroDescIdArg)?.toIntOrNull(),
        )
    }

    override fun toString() =
        "$descRoute?$heroImageUrlArg=$heroImageUrl?$heroNameIdArg=$heroNameId?$heroDescIdArg=$heroDescId"
}
