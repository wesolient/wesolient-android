package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.widget.text.HeaderText

@Composable
fun WesolientHeader(
    title: String,
    icon: Painter? = null,
    onBackPressed: (() -> Unit)? = null,
    secondaryAction: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (onBackPressed != null) {
            WesolientIconButton(
                imageVector = WesolientTheme.icons.back,
                onClick = onBackPressed
            )
        }

        var size = 0

        if (action != null) {
           size += 1
        }

        if (secondaryAction != null) {
            size += 1
        }

        if (onBackPressed != null) {
            size -= 1
        }

        Spacer(
            modifier = Modifier
                .width(48.dp * size),
        )

        Row(
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.let {
                WesolientIcon(
                    modifier = Modifier
                        .size(32.dp),
                    painter = icon,
                    tint = Color.Unspecified
                )
            }

            HeaderText(
                text = title
            )
        }

        Row {
            secondaryAction?.invoke()

            action?.invoke()
        }
    }
}
