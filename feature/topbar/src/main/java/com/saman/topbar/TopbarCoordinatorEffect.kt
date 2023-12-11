package com.saman.topbar

sealed interface TopbarCoordinatorEffect {
    object DeleteSelected: TopbarCoordinatorEffect
    data class OnTabSelect(val index: Int): TopbarCoordinatorEffect
}