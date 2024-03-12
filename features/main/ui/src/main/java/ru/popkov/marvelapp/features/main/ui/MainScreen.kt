package ru.popkov.marvelapp.features.main.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.marvelapp.features.main.domain.model.HeroData
import ru.popkov.marvelapp.theme.Colors
import ru.popkov.marvelapp.theme.InterTextExtraBold28
import ru.popkov.marvelapp.theme.InterTextExtraBold32
import ru.popkov.marvelapp.theme.MarvelTheme
import ru.popkov.marvelapp.theme.Theme
import utils.rememberForeverLazyListState
import kotlin.math.abs

@Composable
internal fun MainScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: MainViewModel = hiltViewModel(),
    onCardClick: (heroImageUrlArg: String, heroNameIdArg: String, heroDescIdArg: String) -> Unit,
) {

    val context = LocalContext.current
    val heroItems by viewModel.heroData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val heroes = heroItems.heroModel
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        errorMessage?.let { snackbarHostState.showSnackbar(message = it) }

        // if internet connection is down, show error
        if (!checkInternetConnection(context)) {
            snackbarHostState.showSnackbar(message = context.getString(R.string.no_internet))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Colors.BackgroundColor)
            .padding(top = Theme.size.huge),
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

        Spacer(modifier = Modifier.weight(1f))

        HeroCards(
            list = heroes,
            onCardClick = onCardClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroCards(
    list: HeroData?,
    onCardClick: (heroImageUrlArg: String, heroNameIdArg: String, heroDescIdArg: String) -> Unit,
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
                    lineTo(size.width, size.height / 3f)
                    close()
                }
                drawPath(path, backgroundColors[centerItemIndex])
            }
    ) {
        LazyRow(
            state = state,
            flingBehavior = flingBehavior,
        ) {
            val heroes = list?.data?.results
            itemsIndexed(heroes ?: emptyList()) { index, hero ->
                Layout(
                    content = {
                        CardItem(
                            state = state,
                            index = heroes?.indexOf(hero) ?: 0,
                            cardText = hero.name,
                            cardImageUrl = "${
                                hero.thumbnail.path.replace(
                                    "http",
                                    "https"
                                )
                            }.${hero.thumbnail.extension}",
                            cardDesc = hero.description,
                            onCardClick = onCardClick
                        )
                    },
                    measurePolicy = { measurable, constraints ->
                        val isRtl = layoutDirection == LayoutDirection.Rtl
                        val placeable = measurable.first().measure(constraints)
                        val maxWidthInPx = maxWidth.roundToPx()
                        val itemWidth = placeable.width
                        val startSpace =
                            if (index == heroes?.lastIndex) (maxWidthInPx - itemWidth) / 2 else 0
                        val endSpace = if (index == 0) (maxWidthInPx - itemWidth) / 2 else 0
                        val width = startSpace + placeable.width + endSpace

                        layout(width, placeable.height) {
                            val x = when {
                                index == 0 && isRtl -> startSpace
                                index == 0 && !isRtl -> endSpace
                                index == heroes?.lastIndex && isRtl -> startSpace
                                index == heroes?.lastIndex && !isRtl -> width - placeable.width - startSpace
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
    cardText: String = "Deadpool",
    cardDesc: String = "Deadpool desc",
    cardImageUrl: String = "https://ibb.co/nnrQ4JG",
    onCardClick: (heroImageUrlArg: String, heroNameIdArg: String, heroDescIdArg: String) -> Unit,
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
            .scale(scale = scale)
            .padding(bottom = Theme.size.large)
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
                modifier = Modifier.size(width = 300.dp, height = 550.dp),
                model = cardImageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "Card image",
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = Theme.size.larger, start = Theme.size.large),
                text = cardText,
                style = InterTextExtraBold32,
                color = Color.White,
            )
        }
    }
}

private fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

@UiModePreviews
@Composable
private fun CardItemPreview() {
    MarvelTheme {
        CardItem { _, _, _ -> }
    }
}
