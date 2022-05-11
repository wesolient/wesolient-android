package com.vastausf.wesolient.presentation.design.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT
import com.vastausf.wesolient.model.data.Workspace
import com.vastausf.wesolient.network.SystemMessage
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.widget.message.SystemMessageWidget

@Composable
fun <T> WesolientList(
    items: List<T>,
    createTitle: (T) -> String,
    onClick: (T) -> Unit = { }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items) { item ->
            Box {
                ListItem(
                    title = createTitle(item),
                    onClick = {
                        onClick(item)
                    }
                )
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
private fun WesolientList_Preview() {
    WesolientTheme {
        WesolientList(
            items = listOf(
                Workspace(
                    title = "Workspace 1",
                    link = ""
                ),
                Workspace(
                    title = "Workspace 2",
                    link = ""
                ),
                Workspace(
                    title = "Workspace 3",
                    link = ""
                )
            ),
            createTitle = { it.title }
        )
    }
}
