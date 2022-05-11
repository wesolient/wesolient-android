package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vastausf.wesolient.presentation.design.token.WesolientTheme

@Composable
fun WesolientFloatingActionButton(
    onClick: () -> Unit
) {
    val colors = WesolientTheme.colors
    val shapes = WesolientTheme.shapes
    val icons = WesolientTheme.icons

    FloatingActionButton(
        shape = shapes.floatingActionButton,
        backgroundColor = colors.primary,
        onClick = onClick
    ) {
        Icon(
            imageVector = icons.plus,
            tint = colors.surface,
            contentDescription = "Add"
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun WesolientFloatingActionButtonPreview() {
    WesolientTheme {
        WesolientFloatingActionButton() { }
    }
}