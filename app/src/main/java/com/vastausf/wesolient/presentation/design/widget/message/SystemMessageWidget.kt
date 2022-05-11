package com.vastausf.wesolient.presentation.design.widget.message

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.network.SystemMessage
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.widget.text.HintText

@Composable
fun SystemMessageWidget(
    message: SystemMessage
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 64.dp,
                top = 4.dp,
                end = 64.dp,
                bottom = 4.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        HintText(
            text = message.content
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
private fun SystemMessageWidget_Preview() {
    WesolientTheme {
        SystemMessageWidget(
            SystemMessage(
                content = "System message",
                timestamp = 1653253487
            )
        )
    }
}
