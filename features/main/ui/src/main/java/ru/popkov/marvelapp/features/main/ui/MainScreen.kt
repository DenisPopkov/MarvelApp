package ru.popkov.marvelapp.features.main.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.marvelapp.theme.InterTextExtraBold28
import ru.popkov.marvelapp.theme.InterTextExtraBold32
import ru.popkov.marvelapp.theme.MarvelTheme

@Composable
internal fun MainScreen(
    onCardClick: (heroImageIdArg: Int) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_main_background),
            contentDescription = "Main background",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Marvel logo",
            )
            Text(
                modifier = Modifier
                    .padding(top = 54.dp),
                text = stringResource(id = R.string.main_screen_title),
                style = InterTextExtraBold28,
                color = Color.White,
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 82.dp)
                    .shadow(elevation = 20.dp)
            ) {
                item {
                    CardItem(
                        onCardClick = onCardClick
                    )
                }
            }
        }
    }
}

@Composable
internal fun CardItem(
    @StringRes cardText: Int = R.string.deadpool_hero,
    @DrawableRes cardImage: Int = R.drawable.ic_deadpool_card_image,
    onCardClick: (heroImageIdArg: Int) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .size(width = 300.dp, height = 500.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .clickable { onCardClick.invoke(cardImage) },
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = cardImage),
            contentScale = ContentScale.Crop,
            contentDescription = "Card image",
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 60.dp, start = 28.dp),
            text = stringResource(id = cardText),
            style = InterTextExtraBold32,
            color = Color.White,
        )
    }
}

@UiModePreviews
@Composable
private fun CardPreview() {
    MarvelTheme {
        Surface {
            CardItem()
        }
    }
}

@UiModePreviews
@Composable
private fun Preview() {
    MarvelTheme {
        Surface {
            MainScreen()
        }
    }
}
