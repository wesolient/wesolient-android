package com.vastausf.wesolient.presentation.design.widget.message

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.network.ClientMessage
import com.vastausf.wesolient.network.Message
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.widget.text.ContentText
import com.vastausf.wesolient.presentation.design.widget.text.SubtitleText
import com.vastausf.wesolient.toPrettyTime

@Composable
fun MessageWidget(
    message: Message
) {
    BoxWithConstraints(
        propagateMinConstraints = true
    ) {
        Column {
            ContentText(
                modifier = Modifier
                    .align(Start),
                text = message.content
            )

            Spacer(
                modifier = Modifier
                    .height(2.dp),
            )

            SubtitleText(
                modifier = Modifier
                    .align(End),
                text = message.timestamp.toPrettyTime()
            )
        }
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
private fun MessageWidget_Preview() {
    WesolientTheme {
        MessageWidget(ClientMessage("Client message", 1653253487))
    }
}
