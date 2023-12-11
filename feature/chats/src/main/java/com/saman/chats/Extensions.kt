package com.saman.chats

internal fun <T> MutableList<T>.toggle(item: T) {
    val isSelected = contains(item)
    if (isSelected) remove(item) else add(item)
}