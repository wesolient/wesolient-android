package com.vastausf.wesolient.presentation.design.token

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalWesolientColors = staticCompositionLocalOf<WesolientColors> {
    error("WesolientColors not provided")
}

data class WesolientColors(
    val primary: Color,
    val primaryGhost: Color,
    val text: Color,
    val textGhost: Color,
    val icon: Color,
    val iconGhost: Color,
    val surface: Color,
    val background: Color,
    val secondary: Color
) {
    fun all(): List<Color> = listOf(
        primary,
        primaryGhost,
        text,
        textGhost,
        icon,
        iconGhost,
        surface,
        background,
        secondary
    )
}