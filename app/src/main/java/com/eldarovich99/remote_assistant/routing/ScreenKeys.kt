package com.eldarovich99.remote_assistant.routing

import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen

//sealed class ScreenKeys : Screen()

class ChatScreen : SupportAppScreen()
class LoginScreen : SupportAppScreen()
class ContactsScreen : SupportAppScreen()
class SingleChatScreen : SupportAppScreen()
class CallScreen : SupportAppScreen()

object ScreenKeys {
    const val CHATS = "chats"
    const val LOGIN = "login"
    const val CONTACTS = "contacts"
    const val SINGLE_CHAT = "single_chat"
    const val CALL_SCREEN = "call_screen"

    fun getScreen(key: String) : Screen {
        return when (key){
            CHATS -> ChatScreen()
            LOGIN -> LoginScreen()
            CONTACTS -> ContactsScreen()
            SINGLE_CHAT -> SingleChatScreen()
            CALL_SCREEN -> CallScreen()
            else -> throw Exception("Unknown screen key")
        }
    }
}