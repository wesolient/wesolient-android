package com.vastausf.wesolient.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vastausf.wesolient.LocalWesolientDatabase
import com.vastausf.wesolient.R
import com.vastausf.wesolient.model.data.Workspace
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.widget.WesolientFloatingActionButton
import com.vastausf.wesolient.presentation.design.widget.WesolientListItem
import com.vastausf.wesolient.presentation.ui.common.FormWithTwoFields
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    val database = LocalWesolientDatabase.current
    val workspaceDao = database.workspaceDao()

    val workspaceItems by workspaceDao.getAll().collectAsState(emptyList())

    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            CreateWorkspaceDialog { first, second ->
                coroutineScope.launch(Dispatchers.IO) {
                    workspaceDao.insert(Workspace(
                        title = first,
                        link = second
                    ))
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                Header()
            },
            floatingActionButton = {
                WesolientFloatingActionButton {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                }
            },
            content = {
                ScopeList(workspaceItems)
            }
        )
    }
}

@Preview
@Composable
private fun Header() {
    val typography = WesolientTheme.typography
    val colors = WesolientTheme.colors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp),
            painter = painterResource(R.drawable.ic_app),
            tint = Color.Unspecified,
            contentDescription = null
        )

        Text(
            text = "Wesolient",
            style = typography.header,
            color = colors.primary
        )
    }
}

@Composable
private fun ScopeList(
    items: List<Workspace>,
    onClick: () -> Unit = { }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items) { item ->
            WesolientListItem(
                title = item.title,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun CreateWorkspaceDialog(
    onCreate: (String, String) -> Unit
) {
    FormWithTwoFields(
        firstFieldHint = "Title",
        secondFieldHint = "Link",
        actionHint = "Create",
        onApply = onCreate
    )
}
