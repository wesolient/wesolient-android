package com.vastausf.wesolient.presentation.design.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.android.style.TextDecorationSpan
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.widget.text.ContentText
import com.vastausf.wesolient.presentation.design.widget.text.TitleText

@Composable
fun DeletionForm(
    targetValue: String,
    onApply: () -> Unit
) {
    var textFieldValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        val annotatedString = buildAnnotatedString {
            append("Type ")

            withStyle(
                style = SpanStyle(
                    color = colors.primary
                )
            ) {
                append(targetValue)
            }

            append(" to confirm")
        }

        TitleText(annotatedString)

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        TransparentTextField(
            boxModifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            value = textFieldValue,
            placeholder = targetValue,
            onValueChange = {
                textFieldValue = it
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            WesolientButton(
                text = "Delete",
                enabled = textFieldValue == targetValue
            ) {
                onApply()

                textFieldValue = ""
            }
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
private fun DeletionForm_Preview() {
    WesolientTheme {
        DeletionForm("Wesolient") {

        }
    }
}
