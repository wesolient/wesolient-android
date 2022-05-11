package com.vastausf.wesolient.presentation.design.token

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

val LocalWesolientTypography = staticCompositionLocalOf<WesolientTypography> {
    error("WesolientTypography not provided")
}

data class WesolientTypography(
    val body: TextStyle,
    val button: TextStyle,
    val title: TextStyle,
    val subtitle: TextStyle,
    val header: TextStyle
) {
    fun all(): List<TextStyle> = listOf(body, button, title, subtitle, header)
}
