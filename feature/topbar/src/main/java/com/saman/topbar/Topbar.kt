package com.saman.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saman.topbar.TopbarContract.UIAction
import com.saman.topbar.TopbarContract.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
    uiState: UIState,
    onAction: (UIAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            title = {
                Text(uiState.title)
            },
            actions = {
                if (uiState.isDeleteButtonVisible) {
                    IconButton(
                        onClick = {
                            onAction(UIAction.OnDeleteClick)
                        }
                    ) {
                        Icon(
                            tint = MaterialTheme.colorScheme.onPrimary,
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                        )
                    }
                }
            }
        )
        TabRow(
            selectedTabIndex = uiState.selectedTabIndex,
        ) {
            uiState.tabs.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .height(48.dp),
                    selected = uiState.selectedTabIndex == index,
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                    onClick = { onAction(UIAction.OnTabClick(index)) }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = tab.name)
                        Spacer(modifier = Modifier.size(8.dp))
                        if (tab.unreadCount > 0) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = MaterialTheme.colorScheme.primary
                            ) {
                                Text(
                                    text = "${tab.unreadCount}")
                            }
                        }
                    }
                }
            }
        }
    }
}