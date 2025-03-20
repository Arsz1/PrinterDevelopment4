package com.example.printerdevelopment4.ui

import android.os.Build
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import com.example.printerdevelopment4.R

val default = FontFamily(
    Font(
        R.font.league_gothic_regular
    )
)
@OptIn(ExperimentalTextApi::class)
val displayFontFamily = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    FontFamily(
        Font(
            R.font.league_gothic_variable_wdth,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(950),
                FontVariation.width(30f),
                FontVariation.slant(-6f),
            )
        )
    )
} else {
    default
}