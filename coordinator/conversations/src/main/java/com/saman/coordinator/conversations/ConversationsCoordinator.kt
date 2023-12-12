package com.saman.coordinator.conversations

import androidx.compose.runtime.Composable
import com.saman.chats.ChatsContract
import com.saman.chats.ChatsCoordinatorEffect
import com.saman.chats.ChatsViewModel
import com.saman.core.collectAsEffectWithLifecycle
import com.saman.topbar.TopbarContract
import com.saman.topbar.TopbarCoordinatorEffect
import com.saman.topbar.TopbarViewModel

@Composable
internal fun ConversationsCoordinator(
    topbarViewModel: TopbarViewModel,
    chatsViewModel: ChatsViewModel,
    onTabSelect: (index: Int) -> Unit
) {
    topbarViewModel.coordinatorEffect.collectAsEffectWithLifecycle {
        when(it) {
            TopbarCoordinatorEffect.DeleteSelected -> {
                chatsViewModel.onAction(ChatsContract.UIAction.DeleteSelected)
            }
            is TopbarCoordinatorEffect.OnTabSelect -> {
                onTabSelect(it.index)
            }
        }
    }
    chatsViewModel.coordinatorEffect.collectAsEffectWithLifecycle {
        when(it) {
            is ChatsCoordinatorEffect.SelectionCount -> {
                topbarViewModel.onAction(TopbarContract.UIAction.SelectionCount(it.count))
            }
            is ChatsCoordinatorEffect.UnreadCount -> {
                topbarViewModel.onAction(TopbarContract.UIAction.UnreadCount(it.count))
            }
        }
    }
}