package com.vastausf.wesolient.presentation.design.widget

import android.content.res.Configuration
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.icons
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.shapes

@Composable
fun WesolientFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        shape = shapes.floatingActionButton,
        backgroundColor = colors.primary,
        onClick = onClick
    ) {
        Icon(
            imageVector = icons.plus,
            tint = colors.background,
            contentDescription = "Add"
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
private fun WesolientFloatingActionButtonPreview() {
    WesolientTheme {
        WesolientFloatingActionButton { }
    }
}