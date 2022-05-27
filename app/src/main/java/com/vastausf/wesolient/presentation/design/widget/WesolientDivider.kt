package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.vastausf.wesolient.presentation.design.token.WesolientTheme

@Composable
fun WesolientDivider() {
    Divider(
        color = WesolientTheme.colors.outline
    )
}
