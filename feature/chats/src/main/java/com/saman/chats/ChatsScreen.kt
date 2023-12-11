package com.saman.chats

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.saman.chats.ChatsContract.UIAction
import com.saman.chats.ChatsContract.UIState

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    uiState: UIState,
    onAction: (UIAction) -> Unit
) {

    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = uiState.scrollPositionIndex.value,
        onFirstVisibleItemIndexChange = {
            onAction(UIAction.ScrollPositionChange(it))
        }
    )

    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        itemsIndexed(uiState.chats) { index, chat ->
            Chat(
                chat = chat,
                isSelected = uiState.selectedChats.contains(chat),
                onChatClick = {
                    onAction(UIAction.ChatClick(chat))
                },
                onChatLongClick = {
                    onAction(UIAction.ChatLongClick(chat))
                },
            )
            if (index < uiState.chats.size - 1) {
                Divider()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Chat(
    modifier: Modifier = Modifier,
    chat: Chat,
    isSelected: Boolean,
    onChatClick: () -> Unit,
    onChatLongClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .combinedClickable(
                onClick = {
                    onChatClick()
                },
                onLongClick = {
                    onChatLongClick()
                }
            )
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.background
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            model = chat.avatar,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(
            modifier = modifier
                .weight(1f)
                .height(48.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = chat.user,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(text = chat.message)
        }
        if (chat.unreadCount > 0) {
            Text(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.error)
                    .padding(4.dp),
                text = "${chat.unreadCount}",
                color = MaterialTheme.colorScheme.onError,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatPreview() {
    Chat(
        chat = Chat(
            id = 2,
            avatar = "https://i.pravatar.cc/150?u=a042581f4e29026704d",
            user = "Saman",
            message = "Chetooori?",
            unreadCount = 2,
        ),
        isSelected = false,
        onChatClick = {},
        onChatLongClick = {},
    )
}