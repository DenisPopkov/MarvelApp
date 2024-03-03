package ru.popkov.marvelapp.features.main.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.marvelapp.features.main.domain.model.HeroCard
import ru.popkov.marvelapp.theme.Colors
import ru.popkov.marvelapp.theme.InterTextExtraBold28
import ru.popkov.marvelapp.theme.InterTextExtraBold32
import ru.popkov.marvelapp.theme.MarvelTheme
import ru.popkov.marvelapp.theme.Theme
import utils.rememberForeverLazyListState
import kotlin.math.abs

@Composable
internal fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onCardClick: (heroImageUrlArg: String, heroNameIdArg: Int, heroDescIdArg: Int) -> Unit,
) {

    val heroItems by viewModel.heroData.collectAsState()
    val heroes = heroItems.heroModel

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = Theme.size.large),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = "https://iili.io/JMnuvbp.png",
                contentDescription = "Marvel logo",
            )
            Text(
                modifier = Modifier
                    .padding(top = Theme.size.larger, bottom = Theme.size.huge),
                text = stringResource(id = R.string.main_screen_title),
                style = InterTextExtraBold28,
                color = Color.White,
            )

            HeroCards(
                list = heroes,
                onCardClick = onCardClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroCards(
    list: List<HeroCard>,
    onCardClick: (heroImageUrlArg: String, heroNameIdArg: Int, heroDescIdArg: Int) -> Unit,
) {

    val state = rememberForeverLazyListState(key = "Overview")
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    val backgroundColors = remember {
        listOf(
            Colors.FirstCardColor,
            Colors.SecondCardColor,
            Colors.ThirdCardColor,
        )
    }

    val centerItemIndex by remember {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            visibleItems.minByOrNull { abs((it.offset + it.size / 2) - viewportCenter) }?.index ?: 0
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val path = Path().apply {
                    moveTo(0f, size.height)
                    lineTo(size.width, size.height)
                    lineTo(size.width, size.height / 2)
                    close()
                }
                drawPath(path, backgroundColors[centerItemIndex])
            }
    ) {
        LazyRow(
            state = state,
            flingBehavior = flingBehavior,
        ) {
            itemsIndexed(list) { index, hero ->
                Layout(
                    content = {
                        CardItem(
                            state = state,
                            index = list.indexOf(hero),
                            cardText = hero.cardText,
                            cardImageUrl = hero.cardImageUrl,
                            cardDesc = hero.cardDesc,
                            onCardClick = onCardClick
                        )
                    },
                    measurePolicy = { measurable, constraints ->
                        val isRtl = layoutDirection == LayoutDirection.Rtl
                        val placeable = measurable.first().measure(constraints)
                        val maxWidthInPx = maxWidth.roundToPx()
                        val itemWidth = placeable.width
                        val startSpace =
                            if (index == list.lastIndex) (maxWidthInPx - itemWidth) / 2 else 0
                        val endSpace = if (index == 0) (maxWidthInPx - itemWidth) / 2 else 0
                        val width = startSpace + placeable.width + endSpace

                        layout(width, placeable.height) {
                            val x = when {
                                index == 0 && isRtl -> startSpace
                                index == 0 && !isRtl -> endSpace
                                index == list.lastIndex && isRtl -> startSpace
                                index == list.lastIndex && !isRtl -> width - placeable.width - startSpace
                                else -> 0
                            }
                            placeable.place(x, 0)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun CardItem(
    state: LazyListState = rememberLazyListState(),
    index: Int = 0,
    @StringRes cardText: Int = R.string.deadpool_hero,
    @StringRes cardDesc: Int = R.string.deadpool_desc,
    cardImageUrl: String = "https://ibb.co/nnrQ4JG",
    onCardClick: (heroImageUrlArg: String, heroNameIdArg: Int, heroDescIdArg: Int) -> Unit,
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
                    ) * 0.20f
                    )
        }
    }

    Card(
        modifier = Modifier
            .size(width = 300.dp, height = 550.dp)
            .scale(scale)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            ) {
                onCardClick.invoke(
                    cardImageUrl, cardText, cardDesc
                )
            },
    ) {
        Box {
            AsyncImage(
                model = cardImageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "Card image",
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = Theme.size.larger, start = Theme.size.large),
                text = stringResource(id = cardText),
                style = InterTextExtraBold32,
                color = Color.White,
            )
        }
    }
}

@UiModePreviews
@Composable
private fun CardItemPreview() {
    MarvelTheme {
        CardItem { _, _, _ -> }
    }
}
