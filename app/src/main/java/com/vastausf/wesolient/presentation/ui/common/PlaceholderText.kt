package com.vastausf.wesolient.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showSystemUi = true
)
@Composable
fun PlaceholderTextPreview() {
    PlaceholderText("Place holder text")
}

@Composable
fun PlaceholderText(
    text: String
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = text,
            style = MaterialTheme.typography.subtitle1
        )
    }
}