package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors

@Composable
fun WesolientScaffold(
    header: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = header,
        floatingActionButton = floatingActionButton,
        backgroundColor = colors.background,
        content = content
    )
}
