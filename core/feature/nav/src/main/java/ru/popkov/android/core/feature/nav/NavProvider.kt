package ru.popkov.android.core.feature.nav

import androidx.navigation.NavGraphBuilder

interface NavProvider {

    data class Route(
        val index: Int,
        val route: String,
        val isStart: Boolean,
    )

    val item: Route?

    fun graph(
        builder: NavGraphBuilder,
    )
}
