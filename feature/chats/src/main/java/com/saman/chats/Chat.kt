package com.saman.chats

data class Chat(
    val id: Int,
    val avatar: String,
    val user: String,
    val message: String,
    val unreadCount: Int,
)