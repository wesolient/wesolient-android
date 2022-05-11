package com.vastausf.wesolient.presentation.design.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import com.vastausf.wesolient.presentation.design.token.WesolientTheme
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.typography

@Composable
private fun BasicTransparentTextField(
    modifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = false,
    contentAlignment: Alignment,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        singleLine = singleLine,
        enabled = enabled,
        textStyle = typography.textField,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(colors.primary),
        decorationBox = { innerTextField ->
            Box(
                modifier = boxModifier,
                contentAlignment = contentAlignment
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = typography.hint
                    )
                }

                innerTextField()
            }
        }
    )
}

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit
) {
    BasicTransparentTextField(
        modifier = modifier,
        boxModifier = boxModifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        enabled = enabled,
        contentAlignment = Alignment.CenterStart,
        keyboardOptions = keyboardOptions,
        placeholder = placeholder
    )
}
