package ru.popkov.marvelapp.features.main.ui.desc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.marvelapp.theme.MarvelTheme

@Composable
internal fun DescScreen(
    onBack: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red).clickable { onBack.invoke() })
}

@UiModePreviews
@Composable
private fun Preview() {
    MarvelTheme {
        Surface {
            DescScreen()
        }
    }
}
