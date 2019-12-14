package com.eldarovich99.remote_assistant.routing

import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen

//sealed class ScreenKeys : Screen()

class ChatScreen : SupportAppScreen()
class LoginScreen : SupportAppScreen()
class ContactsScreen : SupportAppScreen()
class SingleChatScreen : SupportAppScreen()
class CallScreen : SupportAppScreen()
class RestorePasswordScreen : SupportAppScreen()
class QRScreen : SupportAppScreen()

object ScreenKeys {
    const val CHATS = "chats"
    const val LOGIN = "login"
    const val CONTACTS = "contacts"
    const val SINGLE_CHAT = "single_chat"
    const val CALL_SCREEN = "call_screen"
    const val RESTORE_PASSWORD_SCREEN = "restore_password_screen"
    const val QR_DECODE_SCREEN = "qr_decode_screen"
    fun getScreen(key: String) : Screen {
        return when (key){
            CHATS -> ChatScreen()
            LOGIN -> LoginScreen()
            CONTACTS -> ContactsScreen()
            SINGLE_CHAT -> SingleChatScreen()
            CALL_SCREEN -> CallScreen()
            RESTORE_PASSWORD_SCREEN -> RestorePasswordScreen()
            QR_DECODE_SCREEN -> QRScreen()
            else -> throw Exception("Unknown screen key")
        }
    }
}