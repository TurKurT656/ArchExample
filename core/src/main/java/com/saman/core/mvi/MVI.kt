package com.saman.core.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVI<UiState, UiAction, UiEffect> {
    val uiState: StateFlow<UiState>
    val uiEffect: Flow<UiEffect>

    fun onAction(uiAction: UiAction) {}

    fun updateUiState(block: UiState.() -> UiState)

    fun updateUiState(newUiState: UiState)

    fun ViewModel.emitUiEffect(uiEffect: UiEffect)
}