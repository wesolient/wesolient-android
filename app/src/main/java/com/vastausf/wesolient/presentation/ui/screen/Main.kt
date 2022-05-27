package com.vastausf.wesolient.presentation.ui.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.vastausf.wesolient.LocalWesolientDatabase
import com.vastausf.wesolient.Navigation
import com.vastausf.wesolient.R
import com.vastausf.wesolient.model.data.Workspace
import com.vastausf.wesolient.presentation.design.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    val coroutineScope = rememberCoroutineScope()

    val database = LocalWesolientDatabase.current
    val workspaceDao = database.workspaceDao()

    val workspaceItems by workspaceDao.getAll().collectAsState(emptyList())

    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    WesolientBottomSheet(
        modalBottomSheetState = modalBottomSheetState,
        sheetContent = {
            CreateWorkspaceDialog { title, link ->
                coroutineScope.launch(Dispatchers.IO) {
                    val workspace = Workspace(
                        title = title,
                        link = link
                    )

                    workspaceDao.insert(workspace)

                    softwareKeyboardController?.hide()
                    modalBottomSheetState.hide()
                }
            }
        },
        content = {
            WesolientScaffold(
                header = {
                    WesolientHeader(
                        title = "Wesolient",
                        icon = painterResource(R.drawable.ic_app)
                    )
                },
                floatingActionButton = {
                    WesolientFloatingActionButton {
                        coroutineScope.launch {
                            modalBottomSheetState.show()
                        }
                    }
                },
                content = {
                    WesolientList(
                        items = workspaceItems,
                        createTitle = { it.title },
                        onClick = { workspace ->
                            navController.navigate("${Navigation.WORKSPACE.path}/${workspace.id}")
                        }
                    )
                }
            )
        }
    )
}

@Composable
private fun CreateWorkspaceDialog(
    onCreate: (String, String) -> Unit
) {
    TwoFieldsForm(
        firstFieldHint = "Title",
        secondFieldHint = "Link",
        actionHint = "Create",
        onApply = onCreate
    )
}
