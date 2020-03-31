package com.eldarovich99.remote_assistant.routing

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.presentation.QrReaderFragment
import com.eldarovich99.remote_assistant.presentation.view.call.CallFragment
import com.eldarovich99.remote_assistant.presentation.view.chats.ChatsFragment
import com.eldarovich99.remote_assistant.presentation.view.login.LoginFragment
import com.eldarovich99.remote_assistant.presentation.view.login.RestorePasswordFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SingleNavigator(val activity: AppCompatActivity, fragmentHost: Int) : SupportAppNavigator(activity, fragmentHost) {
    override fun createFragment(screen: SupportAppScreen?): Fragment {
        when (screen){
            is ChatScreen -> return ChatsFragment()
            is LoginScreen -> return LoginFragment()
            is CallScreen -> return CallFragment()
            is RestorePasswordScreen -> return RestorePasswordFragment()
            is QRScreen -> return QrReaderFragment()
        }
        throw (Exception("Unknown Fragment"))
    }
}