package ru.popkov.marvelapp.features.main.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.marvelapp.features.main.domain.model.HeroCard
import ru.popkov.marvelapp.theme.InterTextExtraBold28
import ru.popkov.marvelapp.theme.InterTextExtraBold32
import ru.popkov.marvelapp.theme.MarvelTheme
import kotlin.math.abs

@Composable
internal fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onCardClick: (heroImageIdArg: Int) -> Unit = {},
) {

    val heroItems by viewModel.heroData.collectAsState(initial = emptyList())

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
                    .padding(top = 54.dp, bottom = 72.dp),
                text = stringResource(id = R.string.main_screen_title),
                style = InterTextExtraBold28,
                color = Color.White,
            )

            MyCards(list = heroItems, onCardClick = onCardClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyCards(list: List<HeroCard>, onCardClick: (heroImageIdArg: Int) -> Unit) {

    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    LazyRow(
        modifier = Modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        state = state,
        flingBehavior = flingBehavior
    ) {
        items(list) {
            MyCard(
                state = state,
                index = list.indexOf(it),
                cardText = it.cardText,
                cardImage = it.cardImage,
                onCardClick = onCardClick
            )
        }
    }
}

@Composable
private fun MyCard(
    state: LazyListState,
    index: Int,
    @StringRes cardText: Int = R.string.deadpool_hero,
    @DrawableRes cardImage: Int = R.drawable.ic_deadpool_card_image,
    onCardClick: (heroImageIdArg: Int) -> Unit = {},
) {

    val scale by remember {
        derivedStateOf {
            val currentItem = state.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
                ?: return@derivedStateOf 1.0f
            val halfRowWidth = state.layoutInfo.viewportSize.width / 2
            (
                    1f - minOf(
                        1f,
                        abs(currentItem.offset + (currentItem.size / 2) - halfRowWidth).toFloat() / halfRowWidth
                    ) * 0.10f
                    )
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp, 10.dp, 10.dp, 200.dp)
            .scale(scale)
            .zIndex(scale * 10),
        shape = RoundedCornerShape(10.dp),
    ) {
        CardItem(cardText, cardImage, onCardClick)
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
