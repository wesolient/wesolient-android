package com.vastausf.wesolient.network

sealed class Message(
    val content: String,
    val timestamp: Long
)

class ClientMessage(
    content: String,
    timestamp: Long
): Message(content, timestamp)

class SystemMessage(
    content: String,
    timestamp: Long
): Message(content, timestamp)

class ServerMessage(
    content: String,
    timestamp: Long
): Message(content, timestamp)
