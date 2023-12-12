package com.saman.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saman.chats.ChatsContract.UIAction
import com.saman.chats.ChatsContract.UIState
import com.saman.core.coordinator.Coordinator
import com.saman.core.coordinator.coordinator
import com.saman.core.mvi.MVI
import com.saman.core.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject internal constructor(
    private val getChatListUseCase: GetChatListUseCase,
) : ViewModel(),
    MVI<UIState, UIAction, Nothing> by mvi(UIState.initial()),
    Coordinator<ChatsCoordinatorEffect> by coordinator() {

    init {
        onAction(UIAction.LoadData)
    }

    override fun onAction(uiAction: UIAction) {
        when (uiAction) {
            UIAction.LoadData -> {
                getChatList()
            }

            is UIAction.ChatClick -> {
                handleClick(uiAction.chat)
            }

            is UIAction.ChatLongClick -> {
                toggleSelection(uiAction.chat)
            }

            is UIAction.DeleteSelected -> {
                updateUiState {
                    val chats = chats.toMutableList()
                    chats.removeAll(selectedChats)
                    copy(
                        chats = chats,
                        selectedChats = emptyList()
                    )
                }
                emitCoordinatorEffect(ChatsCoordinatorEffect.SelectionCount(0))
            }
            is UIAction.ScrollPositionChange -> {
                updateUiState {
                    scrollPositionIndex.value = uiAction.index
                    copy(scrollPositionIndex = scrollPositionIndex)
                }
            }
        }
    }

    private fun handleClick(chat: Chat) {
        val hasAnySelectedItem = uiState.value.selectedChats.isNotEmpty()
        if (hasAnySelectedItem) {
            toggleSelection(chat)
        } else {
            // TODO("Navigate to detail")
        }
    }

    private fun toggleSelection(chat: Chat) {
        updateUiState {
            val selectedChats = selectedChats.toMutableList()
            selectedChats.toggle(chat)
            copy(selectedChats = selectedChats)
        }
        emitCoordinatorEffect(ChatsCoordinatorEffect.SelectionCount(uiState.value.selectedChats.size))
    }

    private fun getChatList() {
        viewModelScope.launch {
            val chats = getChatListUseCase.invoke()
            updateUiState { copy(chats = chats) }
            val totalUnreadCount = chats.sumOf { it.unreadCount }
            emitCoordinatorEffect(ChatsCoordinatorEffect.UnreadCount(totalUnreadCount))
        }
    }
}