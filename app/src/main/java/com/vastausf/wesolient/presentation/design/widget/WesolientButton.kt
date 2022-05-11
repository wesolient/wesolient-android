package com.vastausf.wesolient.presentation.design.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.shapes
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.typography

@Composable
fun WesolientButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val buttonColors = ButtonDefaults
        .buttonColors(
            backgroundColor = colors.primary,
            disabledBackgroundColor = colors.primaryGhost,
            contentColor = colors.background,
            disabledContentColor = colors.background
        )

    Button(
        modifier = modifier,
        enabled = enabled,
        colors = buttonColors,
        shape = shapes.button,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = text,
            style = typography.button,
            color = colors.background
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
private fun WesolientButtonPreview() {
    WesolientTheme {
        Column {
            WesolientButton(
                text = "Action",
                enabled = true,
                onClick = { }
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            WesolientButton(
                text = "Action",
                enabled = false,
                onClick = { }
            )
        }
    }
}

