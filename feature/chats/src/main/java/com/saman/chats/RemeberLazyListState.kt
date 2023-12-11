package com.saman.chats

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce


private const val DEBOUNCE = 500L

data class Position(var value: Int)

@Composable
fun rememberLazyListState(
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0,
    onFirstVisibleItemIndexChange: ((index: Int) -> Unit)? = null,
    onFirstVisibleItemScrollOffsetChange: ((index: Int) -> Unit)? = null,
): LazyListState {
    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex,
        initialFirstVisibleItemScrollOffset,
    )
    @OptIn(FlowPreview::class)
    LaunchedEffect(key1 = lazyListState) {
        onFirstVisibleItemIndexChange?.let { call ->
            snapshotFlow { lazyListState.firstVisibleItemIndex }
                .debounce(DEBOUNCE)
                .collectLatest {
                    call(it)
                }
        }
        onFirstVisibleItemScrollOffsetChange?.let { call ->
            snapshotFlow { lazyListState.firstVisibleItemScrollOffset }
                .debounce(DEBOUNCE)
                .collectLatest {
                    call(it)
                }
        }
    }
    return lazyListState
}