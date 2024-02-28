package ru.popkov.marvelapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.popkov.android.core.feature.nav.NavProvider
import ru.popkov.android.core.feature.nav.NavigationLaunchedEffect
import ru.popkov.android.core.feature.nav.Navigator

@Composable
fun MainWindow(
    navProviders: Set<NavProvider>,
    navigator: Navigator,
) {
    val navController = rememberNavController()
    val items = navProviders
        .mapNotNull { it.item }
        .sortedBy { it.index }
    val snackbarHostState = remember { SnackbarHostState() }

    NavigationLaunchedEffect(
        navigator = navigator,
        navController = navController,
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = (items.firstOrNull { it.isStart } ?: items.first()).route,
            modifier = Modifier
                .padding(innerPadding),
        ) {
            navProviders.forEach { subGraph ->
                subGraph.graph(this, snackbarHostState)
            }
        }
    }
}
