package com.saman.chats

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

internal class GetChatListUseCase @Inject constructor() {

    suspend operator fun invoke(): List<Chat> {
        delay(2_000)
        return listOf(
            generateChat("Sammuel", "ChetooOoori?", unreadCount = 3),
            generateChat("Alex Aliov", "What happened?"),
            generateChat("Richard Dorri", "Lorem ipsum dolor sit..."),
            generateChat("David Muertta", "ok"),
            generateChat("Max Savi", "Yeahhhh."),
            generateChat("Ana", ":)))"),
            generateChat("Sarah Carry", "will see!"),
            generateChat("Roberto Salvi", "ofc, u can"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Mr Scoot", "Bye for now"),
        )
    }

}

private fun generateChat(
    name: String,
    message: String,
    unreadCount: Int = 0,
) = Chat(
    id = Random.nextInt(10000),
    avatar = "https://i.pravatar.cc/150?u=$name",
    user = name,
    message = message,
    unreadCount = unreadCount,
)