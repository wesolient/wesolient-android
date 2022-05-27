package com.vastausf.wesolient.presentation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vastausf.wesolient.LocalWesolientDatabase
import com.vastausf.wesolient.Navigation
import com.vastausf.wesolient.model.data.Workspace
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.icons
import com.vastausf.wesolient.presentation.design.widget.*
import com.vastausf.wesolient.presentation.design.widget.text.ContentText
import com.vastausf.wesolient.presentation.design.widget.text.HintText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WorkspaceSettingsPreload(
    navController: NavController,
    workspaceId: String
) {
    val database = LocalWesolientDatabase.current

    val workspace by database.workspaceDao().getById(workspaceId).collectAsState(null)

    if (workspace != null) {
        WorkspaceSettings(navController, workspace!!)
    } else {
        PreloadPlaceholder()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun WorkspaceSettings(
    navController: NavController,
    workspace: Workspace
) {
    val coroutineScope = rememberCoroutineScope()

    val database = LocalWesolientDatabase.current

    var title by remember { mutableStateOf(workspace.title) }
    var link by remember { mutableStateOf(workspace.link) }

    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    WesolientBottomSheet(
        modalBottomSheetState = modalBottomSheetState,
        sheetContent = {
            DeletionForm(
                targetValue = workspace.title
            ) {
                coroutineScope.launch(Dispatchers.IO) {
                    database.workspaceDao().delete(workspace)

                    launch(Dispatchers.Main) {
                        navController.popBackStack(Navigation.MAIN.path, false)
                    }
                }
            }
        }
    ) {
        Column {
            WesolientHeader(
                title = "Settings"
            ) {
                WesolientIconButton(
                    imageVector = icons.check
                ) {
                    workspace.title = title
                    workspace.link = link

                    coroutineScope.launch(Dispatchers.IO) {
                        database
                            .workspaceDao()
                            .update(workspace)

                        launch(Dispatchers.Main) {
                            navController.popBackStack()
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                SettingsTextField(
                    title = "Title",
                    value = title,
                    placeholder = "Cool project"
                ) { newTitle ->
                    title = newTitle
                }

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )

                SettingsTextField(
                    title = "Link",
                    value = link,
                    placeholder = "ws://your.fast.api.io/connect"
                ) { newLink ->
                    link = newLink
                }
            }

            DeletionButton {
                coroutineScope.launch {
                    modalBottomSheetState.show()
                }
            }
        }
    }
}

@Composable
private fun SettingsTextField(
    title: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
    ) {
        HintText(
            text = title
        )

        Spacer(
            modifier = Modifier
                .height(8.dp),
        )

        TransparentTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp
                ),
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DeletionButton(
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                role = Role.Button,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WesolientIcon(
            imageVector = icons.remove,
            tint = colors.caution
        )

        Spacer(
            modifier = Modifier
                .width(4.dp),
        )

        ContentText(
            text = "Delete workspace",
            color = colors.caution
        )
    }
}
