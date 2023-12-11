package com.saman.topbar

import androidx.lifecycle.ViewModel
import com.saman.topbar.TopbarContract.UIAction
import com.saman.topbar.TopbarContract.UIState
import com.saman.core.coordinator.Coordinator
import com.saman.core.coordinator.coordinator
import com.saman.core.mvi.MVI
import com.saman.core.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopbarViewModel @Inject constructor() : ViewModel(),
    MVI<UIState, UIAction, Nothing> by mvi(UIState.initial()),
        Coordinator<TopbarCoordinatorEffect> by coordinator() {

    override fun onAction(uiAction: UIAction) {
        when (uiAction) {
            is UIAction.OnTabClick -> {
                updateUiState {
                    copy(selectedTabIndex = uiAction.index)
                }
                emitCoordinatorEffect(TopbarCoordinatorEffect.OnTabSelect(uiAction.index))
            }

            is UIAction.SelectionCount -> {
                updateUiState {
                    val count = uiAction.count
                    val tabs = tabs.toMutableList()
                    tabs[selectedTabIndex] = tabs[selectedTabIndex].copy(selectionCount = count)
                    copy(tabs = tabs)
                }
            }

            UIAction.OnDeleteClick -> {
                emitCoordinatorEffect(TopbarCoordinatorEffect.DeleteSelected)
            }
        }
    }
}