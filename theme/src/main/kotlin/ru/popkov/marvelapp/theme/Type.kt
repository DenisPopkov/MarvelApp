package ru.popkov.marvelapp.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val InterTextExtraBold28 = TextStyle(
    fontSize = 28.sp,
    fontWeight = FontWeight.ExtraBold,
    fontFamily = FontFamily(Font(R.font.inter_extra_bold)),
)

val InterTextExtraBold32 = TextStyle(
    fontSize = 32.sp,
    fontWeight = FontWeight.ExtraBold,
    fontFamily = FontFamily(Font(R.font.inter_extra_bold)),
)

val InterTextExtraBold34 = TextStyle(
    fontSize = 34.sp,
    fontWeight = FontWeight.ExtraBold,
    fontFamily = FontFamily(Font(R.font.inter_extra_bold)),
)

val InterTextBold22 = TextStyle(
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = FontFamily(Font(R.font.inter_bold)),
)
