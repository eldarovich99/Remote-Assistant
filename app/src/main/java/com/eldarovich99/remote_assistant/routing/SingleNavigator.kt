package com.eldarovich99.remote_assistant.routing

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.presentation.view.LoginFragment
import com.eldarovich99.remote_assistant.presentation.view.chats.ChatsFragment
import com.eldarovich99.remote_assistant.presentation.view.contacts.ContactsFragment
import com.eldarovich99.remote_assistant.presentation.view.single_chat.SingleChatFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SingleNavigator(activity: AppCompatActivity, fragmentHost: Int) : SupportAppNavigator(activity, fragmentHost) {
    override fun createFragment(screen: SupportAppScreen?): Fragment {
        when (screen){
            is ChatScreen -> return ChatsFragment()
            is LoginScreen -> return LoginFragment()
            is ContactsScreen -> return ContactsFragment()
            is SingleChatScreen -> return SingleChatFragment()
        }
        throw (Exception("Unknown Fragment"))
    }
}