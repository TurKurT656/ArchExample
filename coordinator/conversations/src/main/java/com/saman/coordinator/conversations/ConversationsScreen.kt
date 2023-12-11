package com.saman.coordinator.conversations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.saman.chats.ChatsScreen
import com.saman.chats.ChatsViewModel
import com.saman.topbar.Topbar
import com.saman.topbar.TopbarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsScreen() {
    val topbarViewModel: TopbarViewModel = hiltViewModel()
    val chatsViewModel: ChatsViewModel = hiltViewModel()

    var tabIndex: Int by remember {
        mutableIntStateOf(0)
    }

    ConversationsCoordinator(
        topbarViewModel = topbarViewModel,
        chatsViewModel = chatsViewModel,
        onTabSelect = {
            tabIndex = it
        }
    )
    Scaffold(
        topBar = {
            val uiState by topbarViewModel.uiState.collectAsState()
            Topbar(
                uiState = uiState,
                onAction = topbarViewModel::onAction,
            )
        }
    ) { paddingValues ->
        if (tabIndex == 0) {
            val uiState by chatsViewModel.uiState.collectAsState()
            ChatsScreen(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onAction = chatsViewModel::onAction,
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.Black),
            )
        }
    }
}