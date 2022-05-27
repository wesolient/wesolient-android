package com.vastausf.wesolient.presentation.design.widget.text

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.typography

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = typography.title,
        color = color ?: colors.content
    )
}

@Composable
fun TitleText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = typography.title,
        color = color ?: colors.content
    )
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
private fun TitleText_Preview() {
    WesolientTheme {
        TitleText(
            text = "Title text"
        )
    }
}
