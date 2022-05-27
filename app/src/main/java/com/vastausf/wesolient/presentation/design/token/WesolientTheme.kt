package com.vastausf.wesolient.presentation.design.token

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vastausf.wesolient.PREVIEW_BACKGROUND_DAY
import com.vastausf.wesolient.PREVIEW_BACKGROUND_NIGHT

object WesolientTheme {
    val colors: WesolientColors
        @Composable
        get() = LocalWesolientColors.current

    val typography: WesolientTypography
        @Composable
        get() = LocalWesolientTypography.current

    val shapes: WesolientShapes
        @Composable
        get() = LocalWesolientShapes.current

    val icons: WesolientIcons
        @Composable
        get() = LocalWesolientIcons.current
}

@Composable
fun WesolientTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) {
        WesolientColors(
            primary = Color(0xFFF44336),
            primaryGhost = Color(0xFFF9928B),
            content = Color(0xFFC8C8D1),
            contentGhost = Color(0xFF696977),
            background = Color(0xFF202227),
            dialogScrim = Color(0xB0202227),
            outline = Color(0xFF2B2E33),
            caution = Color(0xFFF44336),
            clientMessage = Color(0xFF353942),
            serverMessage = Color(0xFF2A2D33)
        )
    } else {
        WesolientColors(
            primary = Color(0xFFF44336),
            primaryGhost = Color(0xFFF9928B),
            content = Color(0xFF202227),
            contentGhost = Color(0xFFB1B2C0),
            background = Color(0xFFFFFFFF),
            dialogScrim = Color(0xB0FFFFFF),
            outline = Color(0xFFFBFBFF),
            caution = Color(0xFFF44336),
            clientMessage = Color(0xFFFFD6D4),
            serverMessage = Color(0xFFF5F5F7)
        )
    }

    val typography = WesolientTypography(
        content = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            letterSpacing = 0.sp,
            color = colors.content
        ),
        textField = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
            color = colors.content
        ),
        hint = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
            color = colors.contentGhost
        ),
        button = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            letterSpacing = 0.sp,
            color = colors.background
        ),
        title = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
            color = colors.content
        ),
        subtitle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
            color = colors.contentGhost
        ),
        header = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 26.sp,
            letterSpacing = 0.sp,
            color = colors.primary
        )
    )

    val shapes = WesolientShapes(
        button = RoundedCornerShape(12.dp),
        floatingActionButton = RoundedCornerShape(50),
        message = RoundedCornerShape(16.dp),
        bottomSheet = RoundedCornerShape(16.dp),
        dropdownMenu = RoundedCornerShape(8.dp)
    )

    val icons = WesolientIcons(
        plus = Icons.Rounded.Add,
        back = Icons.Rounded.ArrowBack,
        remove = Icons.Rounded.Delete,
        connect = Icons.Rounded.Link,
        disconnect = Icons.Rounded.LinkOff,
        send = Icons.Rounded.Send,
        templates = Icons.Rounded.AlternateEmail,
        settings = Icons.Rounded.Settings,
        check = Icons.Rounded.Check
    )

    CompositionLocalProvider(
        LocalWesolientColors provides colors,
        LocalWesolientTypography provides typography,
        LocalWesolientShapes provides shapes,
        LocalWesolientIcons provides icons,
        content = content
    )
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
private fun WesolientColors_Preview() {
    WesolientTheme {
        val colors = WesolientTheme.colors.all()

        LazyRow {
            items(colors) { color ->
                Surface(
                    modifier = Modifier
                        .background(color)
                        .padding(24.dp)
                ) { }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun WesolientTypography_Preview() {
    WesolientTheme {
        val typography = WesolientTheme.typography.all()

        Surface {
            Column {
                typography.forEach { textStyle ->
                    Text("Wesolient typography", style = textStyle)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun WesolientShapes_Preview() {
    WesolientTheme {
        val shapes = WesolientTheme.shapes.all()

        Surface {
            LazyRow {
                items(shapes) { shape ->
                    Surface(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(LocalWesolientColors.current.primary, shape)
                            .padding(24.dp)
                    ) { }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun WesolientIcons_Preview() {
    WesolientTheme {
        val icons = WesolientTheme.icons.all()

        Surface {
            LazyRow {
                items(icons) { icon ->
                    Icon(
                        modifier = Modifier
                            .padding(8.dp),
                        imageVector = icon,
                        contentDescription = "Icon"
                    )
                }
            }
        }
    }
}
