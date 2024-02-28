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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.marvelapp.features.main.domain.model.HeroCard
import ru.popkov.marvelapp.theme.InterTextExtraBold28
import ru.popkov.marvelapp.theme.InterTextExtraBold32
import kotlin.math.abs

@Composable
internal fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onCardClick: (heroImageIdArg: Int, heroNameIdArg: Int, heroDescIdArg: Int) -> Unit,
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

            HeroCards(
                list = heroItems,
                onCardClick = onCardClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroCards(
    list: List<HeroCard>,
    onCardClick: (heroImageIdArg: Int, heroNameIdArg: Int, heroDescIdArg: Int) -> Unit,
) {

    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    LazyRow(
        state = state,
        flingBehavior = flingBehavior,
        contentPadding = PaddingValues(horizontal = 30.dp),
    ) {
        items(list) {
            CardItem(
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
private fun CardItem(
    state: LazyListState = rememberLazyListState(),
    index: Int = 0,
    @StringRes cardText: Int = R.string.deadpool_hero,
    @DrawableRes cardImage: Int = R.drawable.ic_deadpool_card_image,
    onCardClick: (heroImageIdArg: Int, heroNameIdArg: Int, heroDescIdArg: Int) -> Unit,
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

    val heroName = stringResource(id = cardText)

    Card(
        modifier = Modifier
            .size(width = 300.dp, height = 550.dp)
            .scale(scale)
            .clickable {
                onCardClick.invoke(
                    cardImage, cardText,
                        when {
                        heroName.contains("Deadpool") -> R.string.deadpool_desc
                        heroName.contains("Iron") -> R.string.iron_man_desc
                        else -> R.string.spider_man_desc
                    }
                )
            },
        shape = RoundedCornerShape(10.dp),
    ) {
        Box {
            Image(
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
}
