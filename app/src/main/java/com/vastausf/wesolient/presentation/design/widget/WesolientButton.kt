package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vastausf.wesolient.presentation.design.token.WesolientTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WesolientButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val colors = WesolientTheme.colors
    val typography = WesolientTheme.typography
    val shapes = WesolientTheme.shapes

    val buttonColors = ButtonDefaults
        .buttonColors(
            backgroundColor = colors.primary,
            disabledBackgroundColor = colors.primaryGhost,
            contentColor = colors.surface,
            disabledContentColor = colors.surface
        )

    Button(
        modifier = Modifier,
        enabled = enabled,
        colors = buttonColors,
        shape = shapes.button,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = typography.button,
            color = colors.surface
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun WesolientButtonPreview() {
    WesolientTheme {
        Column {
            WesolientButton(
                text = "Action",
                enabled = true,
                onClick = { }
            )

            WesolientButton(
                text = "Action",
                enabled = false,
                onClick = { }
            )
        }
    }
}

