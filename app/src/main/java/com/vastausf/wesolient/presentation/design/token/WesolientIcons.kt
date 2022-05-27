package com.vastausf.wesolient.presentation.design.token

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector

val LocalWesolientIcons = staticCompositionLocalOf<WesolientIcons> {
    error("WesolientIcons not provided")
}

data class WesolientIcons(
    val plus: ImageVector,
    val back: ImageVector,
    val remove: ImageVector,
    val connect: ImageVector,
    val disconnect: ImageVector,
    val send: ImageVector,
    val templates: ImageVector,
    val settings: ImageVector,
    val check: ImageVector
) {
    fun all(): List<ImageVector> = listOf(
        plus,
        back,
        remove,
        connect,
        disconnect,
        send,
        templates,
        settings,
        check
    )
}
