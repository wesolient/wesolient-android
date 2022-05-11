package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.presentation.design.token.WesolientTheme

@Composable
fun WesolientListItem(
    title: String,
    enabled: Boolean = true,
    onClick: () -> Unit = { }
) {
    val colors = WesolientTheme.colors
    val typography = WesolientTheme.typography

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp, 8.dp),
            text = title,
            style = typography.title,
            color = colors.text
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun WesolientListItemPreview() {
    WesolientTheme {
        Column {
            WesolientListItem(
                title = "Title",
                onClick = { }
            )

            ThickHorizontalSpacer()

            WesolientListItem(
                title = "Title",
                onClick = { }
            )
        }
    }
}

