package com.vastausf.wesolient.presentation.design.widget.text

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.typography

@Composable
fun PlaceholderText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            textAlign = TextAlign.Center,
            text = text,
            style = typography.hint,
            color = color ?: colors.contentGhost
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = PREVIEW_BACKGROUND_DAY
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_BACKGROUND_NIGHT
)
fun PlaceholderText_Preview() {
    WesolientTheme {
        PlaceholderText("Placeholder text")
    }
}
