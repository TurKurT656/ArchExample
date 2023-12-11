package com.saman.core.coordinator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface Coordinator<CoordinatorEffect> {

    val coordinatorEffect: Flow<CoordinatorEffect>
    fun ViewModel.emitCoordinatorEffect(coordinatorEffect: CoordinatorEffect)
}

class CoordinatorDelegate<CoordinatorEffect> : Coordinator<CoordinatorEffect> {
    private val _coordinatorEffect = Channel<CoordinatorEffect>()
    override val coordinatorEffect: Flow<CoordinatorEffect> = _coordinatorEffect.receiveAsFlow()

    override fun ViewModel.emitCoordinatorEffect(coordinatorEffect: CoordinatorEffect) {
        viewModelScope.launch {
            _coordinatorEffect.send(coordinatorEffect)
        }
    }
}

fun <CoordinatorEffect> coordinator() = CoordinatorDelegate<CoordinatorEffect>()
