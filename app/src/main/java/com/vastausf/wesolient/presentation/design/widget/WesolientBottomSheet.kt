package com.vastausf.wesolient.presentation.design.widget

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.shapes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WesolientBottomSheet(
    modalBottomSheetState: ModalBottomSheetState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    val currentModalBottomSheetState = modalBottomSheetState.currentValue

    var backHandlerEnabled by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    val bottomSheetShape = shapes.bottomSheet

    val focusManager = LocalFocusManager.current

    LaunchedEffect(currentModalBottomSheetState) {
        backHandlerEnabled = if (currentModalBottomSheetState == ModalBottomSheetValue.Hidden) {
            focusManager.clearFocus(false)

            false
        } else {
            true
        }
    }

    BackHandler(backHandlerEnabled) {
        backHandlerEnabled = false

        coroutineScope.launch {
            modalBottomSheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        scrimColor = colors.dialogScrim,
        sheetBackgroundColor = Color.Transparent,
        sheetElevation = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = bottomSheetShape
                    )
                    .background(
                        color = colors.background,
                        shape = bottomSheetShape
                    )
                    .border(
                        width = 1.dp,
                        color = colors.outline,
                        shape = bottomSheetShape
                    ),
            ) {
                sheetContent()
            }
        },
        content = content
    )
}
