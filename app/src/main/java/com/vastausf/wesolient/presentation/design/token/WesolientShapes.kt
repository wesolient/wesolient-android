package com.vastausf.wesolient.presentation.design.token

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

val LocalWesolientShapes = staticCompositionLocalOf<WesolientShapes> {
    error("WesolientShapes not provided")
}

data class WesolientShapes(
    val button: CornerBasedShape,
    val floatingActionButton: CornerBasedShape,
    val message: CornerBasedShape,
    val bottomSheet: CornerBasedShape,
    val dropdownMenu: CornerBasedShape
) {
    fun all(): List<Shape> = listOf(
        button,
        floatingActionButton,
        message,
        bottomSheet,
        dropdownMenu
    )
}
