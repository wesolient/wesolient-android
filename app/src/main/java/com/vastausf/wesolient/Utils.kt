package com.vastausf.wesolient

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.text.SimpleDateFormat
import java.util.*

const val PREVIEW_BACKGROUND_DAY: Long = 0xFFFFFF

const val PREVIEW_BACKGROUND_NIGHT: Long = 0x1E212B

fun OkHttpClient.newWebsocket(url: String, listener: WebSocketListener): WebSocket {
    Log.d("url", url)
    val request = Request
        .Builder()
        .url(url)
        .build()

    return newWebSocket(request, listener)
}

@Composable
fun Long.toPrettyTime(): String {
    val dateFormatter by remember {
        mutableStateOf(
            SimpleDateFormat(
                "hh:mm:ss",
                Locale.getDefault()
            )
        )
    }

    return dateFormatter.format(this)
}

private val calendar: Calendar = Calendar.getInstance()

val nowInMillis: Long = calendar.timeInMillis
