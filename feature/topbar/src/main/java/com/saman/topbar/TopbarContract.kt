package com.saman.topbar

object TopbarContract {
    data class UIState(
        val tabs: List<Tab>,
        val selectedTabIndex: Int,
    ) {

        val title: String
            get() {
                val tab = tabs[selectedTabIndex]
                return if (tab.selectionCount == 0) tab.name else "Selected: ${tab.selectionCount}"
            }

        val isDeleteButtonVisible: Boolean
            get() {
                val tab = tabs[selectedTabIndex]
                return tab.selectionCount > 0
            }

        companion object {
            fun initial() = UIState(
                tabs = listOf(
                    Tab.initial("Chats"),
                    Tab.initial("Channels"),
                ),
                selectedTabIndex = 0,
            )
        }
    }

    sealed interface UIAction {
        object OnDeleteClick : UIAction
        data class OnTabClick(val index: Int): UIAction
        data class SelectionCount(val count: Int): UIAction
        data class UnreadCount(val count: Int) : UIAction
    }
}