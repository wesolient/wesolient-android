package com.vastausf.wesolient.presentation.design.token

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalWesolientColors = staticCompositionLocalOf<WesolientColors> {
    error("WesolientColors not provided")
}

class WesolientColors(
    val primary: Color,
    val primaryGhost: Color,
    val content: Color,
    val contentGhost: Color,
    val background: Color,
    val dialogScrim: Color,
    val outline: Color,
    val divider: Color,
    val clientMessage: Color,
    val serverMessage: Color,
) {
    fun all(): List<Color> = listOf(
        primary,
        primaryGhost,
        content,
        contentGhost,
        background,
        dialogScrim,
        outline,
        divider,
        clientMessage,
        serverMessage
    )
}
