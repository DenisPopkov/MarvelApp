package ru.popkov.marvelapp.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import ru.popkov.marvelapp.theme.Colors.BackgroundColor
import ru.popkov.marvelapp.theme.Colors.Pink40
import ru.popkov.marvelapp.theme.Colors.Pink80
import ru.popkov.marvelapp.theme.Colors.Purple40
import ru.popkov.marvelapp.theme.Colors.Purple80
import ru.popkov.marvelapp.theme.Colors.PurpleGrey40
import ru.popkov.marvelapp.theme.Colors.PurpleGrey80

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
)

@Composable
fun MarvelTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = BackgroundColor.toArgb()
            window.navigationBarColor = BackgroundColor.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

object Theme {
    val size: Size
        @Composable
        @ReadOnlyComposable
        get() = Size()
}
