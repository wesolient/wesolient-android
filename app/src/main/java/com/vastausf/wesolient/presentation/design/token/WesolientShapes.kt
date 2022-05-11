package com.vastausf.wesolient.presentation.design.token

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

val LocalWesolientShapes = staticCompositionLocalOf<WesolientShapes> {
    error("WesolientShapes not provided")
}

data class WesolientShapes(
    val button: Shape,
    val floatingActionButton: Shape,
    val spacer: Shape
) {
    fun all(): List<Shape> = listOf(button, floatingActionButton, spacer)
}
