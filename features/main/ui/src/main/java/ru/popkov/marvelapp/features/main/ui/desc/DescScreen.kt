package ru.popkov.marvelapp.features.main.ui.desc

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.popkov.marvelapp.features.main.ui.R
import ru.popkov.marvelapp.theme.InterTextBold22
import ru.popkov.marvelapp.theme.InterTextExtraBold34
import ru.popkov.marvelapp.theme.Theme

@Composable
internal fun DescScreen(
    viewModel: DescViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = viewModel.heroImageUrl,
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
                text = viewModel.heroNameId.toString(),
                style = InterTextExtraBold34,
                color = Color.White,
            )
            Text(
                modifier = Modifier
                    .padding(top = Theme.size.large),
                text = viewModel.heroDescId.toString(),
                style = InterTextBold22,
                color = Color.White,
            )
        }
    }
}
