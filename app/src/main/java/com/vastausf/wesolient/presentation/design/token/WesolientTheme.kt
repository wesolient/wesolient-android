package com.vastausf.wesolient.presentation.design.token

import androidx.compose.foundation.background
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
    val colors = WesolientColors(
        primary = Color(0xFFF44336),
        primaryGhost = Color(0xFFF9928B),
        text = Color(0xFF1F2033),
        textGhost = Color(0xFF707070),
        icon = Color(0xFF1F2033),
        iconGhost = Color(0xFF707070),
        surface = Color(0xFFFFFFFF),
        background = Color(0xFFF8F2F4),
        secondary = Color(0xFFF8F2F4)
    )

    val typography = WesolientTypography(
        body = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            letterSpacing = 0.sp
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.sp
        ),
        title = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            letterSpacing = 0.sp
        ),
        subtitle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            letterSpacing = 0.sp
        ),
        header = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 26.sp,
            letterSpacing = 0.sp
        )
    )

    val shapes = WesolientShapes(
        button = RoundedCornerShape(4.dp),
        floatingActionButton = RoundedCornerShape(50),
        spacer = RoundedCornerShape(50)
    )

    val icons = WesolientIcons(
        plus = Icons.Rounded.Add,
        back = Icons.Rounded.ArrowBack,
        remove = Icons.Rounded.Clear,
        connect = Icons.Rounded.Link,
        disconnect = Icons.Rounded.LinkOff,
        send = Icons.Rounded.Send,
        templates = Icons.Rounded.AlternateEmail,
        settings = Icons.Rounded.Settings
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
@Preview(showBackground = true)
private fun WesolientColorsPreview() {
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
private fun WesolientTypographyPreview() {
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
private fun WesolientShapesPreview() {
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
private fun WesolientIconsPreview() {
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
