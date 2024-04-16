package ru.popkov.marvelapp.features.main.ui.desc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.marvelapp.features.main.domain.model.Hero
import ru.popkov.marvelapp.features.main.ui.R
import ru.popkov.marvelapp.features.main.ui.utils.checkInternetConnection
import ru.popkov.marvelapp.theme.InterTextBold22
import ru.popkov.marvelapp.theme.InterTextExtraBold34
import ru.popkov.marvelapp.theme.MarvelTheme
import ru.popkov.marvelapp.theme.Theme

@Composable
internal fun DescScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: DescViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
) {

    val context = LocalContext.current
    val heroItem by viewModel.heroData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(errorMessage) {
        errorMessage?.let { snackbarHostState.showSnackbar(message = it) }

        // if internet connection is down, show error
        if (!checkInternetConnection(context)) {
            snackbarHostState.showSnackbar(message = context.getString(R.string.no_internet))
        }
    }

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Description(
            hero = heroItem.heroModel,
            onBack = onBack,
        )

        AnimatedVisibility(visible = heroItem.isLoading) {
            CircularProgressIndicator(color = Color.LightGray)
        }
    }
}

@Composable
private fun Description(
    hero: Hero? = null,
    onBack: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = hero?.imageUrl,
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            fallback = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = "Hero image",
            contentScale = ContentScale.Crop,
        )
        Image(
            modifier = Modifier
                .clickable { onBack.invoke() }
                .padding(all = Theme.size.medium),
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "Back to main screen",
        )
        Column(
            modifier = Modifier
                .padding(start = Theme.size.medium, bottom = Theme.size.large)
                .align(Alignment.BottomStart),
        ) {
            Text(
                text = hero?.name ?: "",
                style = InterTextExtraBold34,
                color = Color.White,
            )
            Text(
                modifier = Modifier
                    .padding(top = Theme.size.large),
                text = hero?.description ?: "",
                style = InterTextBold22,
                color = Color.White,
            )
        }
    }
}

@UiModePreviews
@Composable
private fun Preview() {
    MarvelTheme {
        Description(
            hero = Hero(
                id = 0,
                name = "Deadpool",
                description = "Deadpool description",
                imageUrl = "",
            )
        )
    }
}
