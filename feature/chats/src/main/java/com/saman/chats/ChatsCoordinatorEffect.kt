package com.saman.chats

sealed interface ChatsCoordinatorEffect {
    data class SelectionCount(val count: Int): ChatsCoordinatorEffect
}