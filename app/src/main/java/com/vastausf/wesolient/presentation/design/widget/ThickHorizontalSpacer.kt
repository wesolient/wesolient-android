package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.presentation.design.token.WesolientTheme

@Composable
fun ThickHorizontalSpacer() {
    Spacer(
        modifier = Modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(WesolientTheme.colors.secondary, WesolientTheme.shapes.spacer)
    )
}

@Composable
@Preview(showBackground = true)
private fun ThickHorizontalSpacerPreview() {
    WesolientTheme {
        Column {
            WesolientButton(text = "Button 1") { }
            ThickHorizontalSpacer()
            WesolientButton(text = "Button 2") { }
        }
    }
}
