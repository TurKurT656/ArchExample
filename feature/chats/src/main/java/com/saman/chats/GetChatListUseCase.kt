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
            generateChat("David Muertta", "ok", unreadCount = 2),
            generateChat("Max Savi", "Yeahhhh."),
            generateChat("Ana", ":)))"),
            generateChat("Sarah Carry", "will see!"),
            generateChat("Roberto Salvi", "ofc, u can"),
            generateChat("Mr Scoot", "Bye for now"),
            generateChat("Invoker", "Glorious invocation!"),
            generateChat("Alice Johnson", "Highly recommend it."),
            generateChat("Bob Smith", "Any fun plans?"),
            generateChat("Charlie Brown", "Had a productive day at work"),
            generateChat("Dana Miller", " Trying out a new recipe tonight"),
            generateChat("Eddie Turner", "Nature is so refreshing."),
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