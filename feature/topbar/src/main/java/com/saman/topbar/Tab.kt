package com.saman.topbar

data class Tab(
    val name: String,
    val selectionCount: Int,
    val unreadCount: Int,
) {

    companion object {
        fun initial(name: String) = Tab(name, 0, 0)
    }

}