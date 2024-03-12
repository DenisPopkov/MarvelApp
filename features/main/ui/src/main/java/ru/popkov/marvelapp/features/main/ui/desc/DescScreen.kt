package ru.popkov.marvelapp.features.main.ui.desc

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import ru.popkov.marvelapp.features.main.ui.R
import ru.popkov.marvelapp.theme.InterTextBold22
import ru.popkov.marvelapp.theme.InterTextExtraBold34
import ru.popkov.marvelapp.theme.Theme
import utils.checkInternetConnection
import utils.convertUrl

@Composable
internal fun DescScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: DescViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
) {

    val context = LocalContext.current
    val heroItem by viewModel.heroData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val hero = heroItem.heroModel?.data?.results?.first()

    LaunchedEffect(Unit) {
        errorMessage?.let { snackbarHostState.showSnackbar(message = it) }

        // if internet connection is down, show error
        if (!checkInternetConnection(context)) {
            snackbarHostState.showSnackbar(message = context.getString(R.string.no_internet))
        } else {
            viewModel.getHero()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = convertUrl(
                url = hero?.thumbnail?.path ?: "",
                extension = hero?.thumbnail?.extension ?: ""
            ),
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
