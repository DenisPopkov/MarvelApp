package ru.popkov.composemvi.features.spotlight.nav

import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

object SpotlightDestination : Destination,
    DestinationDefinition(
        route = "spotlight",
    )