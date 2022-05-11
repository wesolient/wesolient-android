package com.vastausf.wesolient.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vastausf.wesolient.LocalWesolientDatabase
import com.vastausf.wesolient.Navigation
import com.vastausf.wesolient.model.data.Workspace
import com.vastausf.wesolient.network.*
import com.vastausf.wesolient.newWebsocket
import com.vastausf.wesolient.nowInMillis
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.colors
import com.vastausf.wesolient.presentation.design.token.WesolientTheme.icons
import com.vastausf.wesolient.presentation.design.widget.PreloadPlaceholder
import com.vastausf.wesolient.presentation.design.widget.TransparentTextField
import com.vastausf.wesolient.presentation.design.widget.WesolientIconButton
import com.vastausf.wesolient.presentation.design.widget.WesolientProgress
import com.vastausf.wesolient.presentation.design.widget.message.ClientMessageWidget
import com.vastausf.wesolient.presentation.design.widget.message.ServerMessageWidget
import com.vastausf.wesolient.presentation.design.widget.message.SystemMessageWidget
import com.vastausf.wesolient.presentation.design.widget.text.HeaderText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

@Composable
fun WorkspaceScreenPreload(
    navController: NavController,
    uid: String
) {
    val database = LocalWesolientDatabase.current

    val workspace by database.workspaceDao().getByUid(uid).collectAsState(null)

    if (workspace != null) {
        WorkspaceScreen(navController, workspace!!)
    } else {
        PreloadPlaceholder()
    }
}

@Composable
private fun WorkspaceScreen(
    navController: NavController,
    workspace: Workspace
) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val messagesListState = rememberLazyListState()

    var webSocket by remember { mutableStateOf<WebSocket?>(null) }

    var connectionState by remember { mutableStateOf(ConnectionState.DISCONNECTED) }

    var messages by remember { mutableStateOf(emptyArray<Message>()) }

    DisposableEffect(Unit) {
        onDispose {
            webSocket?.close(WebSocketClosureCode.NORMAL.code, null)
        }
    }

    val listener by remember {
        mutableStateOf(
            WebSocketListener(
                onWebSocketChange = { newWebSocket ->
                    webSocket = newWebSocket
                },
                onMessageReceived = { message ->
                    messages += message
                }
            ) { newConnectionState ->
                val title = when (newConnectionState) {
                    ConnectionState.CONNECTING -> "Connecting"
                    ConnectionState.CONNECTED -> "Connected"
                    ConnectionState.DISCONNECTING -> "Disconnecting"
                    ConnectionState.DISCONNECTED -> "Disconnected"
                    ConnectionState.FAILED -> "Failed"
                }

                messages += SystemMessage(title, nowInMillis)

                connectionState = newConnectionState
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            WesolientIconButton(
                imageVector = icons.back
            ) {
                navController.popBackStack(Navigation.MAIN.path, false)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                HeaderText(text = workspace.title)
            }

            ConnectionSwitcher(
                connectionState = connectionState,
                onDisconnectClick = {
                    connectionState = ConnectionState.DISCONNECTING

                    webSocket?.close(WebSocketClosureCode.NORMAL.code, null)
                },
                onConnectClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        connectionState = ConnectionState.CONNECTING

                        val okHttpClient = OkHttpClient()

                        try {
                            webSocket = okHttpClient.newWebsocket(workspace.link, listener)
                        } catch (e: Exception) {
                            connectionState = ConnectionState.FAILED

                            messages += SystemMessage("Invalid link", nowInMillis)
                        }
                    }
                }
            )
        }

        Divider(
            color = colors.divider
        )

        LaunchedEffect(messages) {
            coroutineScope.launch {
                messagesListState.animateScrollToItem(maxOf(messages.lastIndex, 0))
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = messagesListState
        ) {
            items(messages) { message ->
                when (message) {
                    is ClientMessage -> {
                        ClientMessageWidget(message)
                    }
                    is SystemMessage -> {
                        SystemMessageWidget(message)
                    }
                    is ServerMessage -> {
                        ServerMessageWidget(message)
                    }
                }
            }
        }

        BottomBar(webSocket) { message ->
            messages += message
        }
    }
}

private fun WebSocketListener(
    onWebSocketChange: (WebSocket?) -> Unit,
    onMessageReceived: (Message) -> Unit,
    onConnectionStateChange: (ConnectionState) -> Unit
): WebSocketListener {
    return object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            onWebSocketChange(webSocket)

            onConnectionStateChange(ConnectionState.CONNECTED)
        }

        override fun onFailure(
            webSocket: WebSocket,
            t: Throwable,
            response: Response?
        ) {
            onWebSocketChange(null)

            onConnectionStateChange(ConnectionState.FAILED)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            onConnectionStateChange(ConnectionState.DISCONNECTING)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            onWebSocketChange(null)

            onConnectionStateChange(ConnectionState.DISCONNECTED)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            onMessageReceived(ServerMessage(text, nowInMillis))
        }
    }
}

@Composable
private fun ConnectionSwitcher(
    connectionState: ConnectionState,
    onDisconnectClick: () -> Unit = { },
    onConnectClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier
            .size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        when (connectionState) {
            ConnectionState.DISCONNECTED, ConnectionState.FAILED -> {
                WesolientIconButton(
                    imageVector = icons.connect,
                    onClick = onConnectClick
                )
            }
            ConnectionState.CONNECTED -> {
                WesolientIconButton(
                    imageVector = icons.disconnect,
                    onClick = onDisconnectClick
                )
            }
            ConnectionState.CONNECTING, ConnectionState.DISCONNECTING -> {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    WesolientProgress()
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    currentWebSocket: WebSocket?,
    onMessageSent: (Message) -> Unit
) {
    Column(
        modifier = Modifier
            .height(56.dp)
            .background(colors.background)
    ) {
        Divider(
            color = colors.divider
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            var messageFieldValue by remember { mutableStateOf("") }

            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )

            TransparentTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                value = messageFieldValue,
                placeholder = "Type message here..."
            ) { newValue ->
                messageFieldValue = newValue
            }

            WesolientIconButton(
                modifier = Modifier
                    .size(48.dp),
                imageVector = icons.send,
                tint = colors.primary,
            ) {
                val messageWasEnqueued = currentWebSocket?.send(messageFieldValue) ?: false

                if (messageWasEnqueued) {
                    onMessageSent(
                        ClientMessage(messageFieldValue, nowInMillis)
                    )
                    messageFieldValue = ""
                }
            }
        }
    }
}
