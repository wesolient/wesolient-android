package com.vastausf.wesolient.presentation.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.presentation.design.widget.WesolientButton
import kotlinx.coroutines.launch

@Composable
fun FormWithTwoFields(
    firstFieldHint: String,
    secondFieldHint: String,
    actionHint: String,
    onApply: (first: String, second: String) -> Unit
) {
    var firstField by remember { mutableStateOf("") }
    var secondField by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .padding(16.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column {
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
                WesolientButton(text = actionHint) {
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
}
