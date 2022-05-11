package com.vastausf.wesolient.presentation.design.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.presentation.design.token.WesolientTheme

@Composable
fun TwoFieldsForm(
    firstFieldHint: String,
    secondFieldHint: String,
    actionHint: String,
    firstFieldFilter: (String) -> Boolean = { it.isNotBlank() },
    secondFieldFilter: (String) -> Boolean = { it.isNotBlank() },
    onApply: (first: String, second: String) -> Unit
) {
    var firstField by remember { mutableStateOf("") }
    var secondField by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        TransparentTextField(
            boxModifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            value = firstField,
            placeholder = firstFieldHint,
            onValueChange = {
                firstField = it
            }
        )

        TransparentTextField(
            boxModifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            value = secondField,
            placeholder = secondFieldHint,
            onValueChange = {
                secondField = it
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            WesolientButton(
                text = actionHint,
                enabled = firstFieldFilter(firstField) && secondFieldFilter(secondField)
            ) {
                onApply(
                    firstField,
                    secondField
                )

                firstField = ""
                secondField = ""
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
private fun TwoFieldsForm_Preview() {
    WesolientTheme {
        TwoFieldsForm(
            firstFieldHint = "First field",
            secondFieldHint = "Second field",
            actionHint = "Action"
        ) { first, second ->

        }
    }
}
