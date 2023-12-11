package com.saman.chats

class ChatsContract {
    data class UIState(
        val chats : List<Chat>,
        val selectedChats : List<Chat>,
    ) {
        companion object {
            fun initial() = UIState(
                chats = emptyList(),
                selectedChats = emptyList(),
            )
        }
    }

    sealed interface UIAction {
        object LoadData : UIAction
        data class ChatLongClick(val chat: Chat) : UIAction
        data class ChatClick(val chat: Chat) : UIAction
        object DeleteSelected: UIAction
    }
}