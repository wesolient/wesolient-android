package com.vastausf.wesolient

import androidx.compose.runtime.staticCompositionLocalOf
import com.vastausf.wesolient.model.WesolientDatabase

val LocalWesolientDatabase = staticCompositionLocalOf<WesolientDatabase> {
    error("Local WesolientDatabase not set")
}