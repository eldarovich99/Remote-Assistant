package com.eldarovich99.remote_assistant.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.routing.ChatScreen
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override fun dispatchKeyEvent(event: KeyEvent?){
        when (event?.keyCode){
            KeyEvent.KEYCODE_DPAD_CENTER -> {}
            KeyEvent.KEYCODE_F1 -> {}
            KeyEvent.KEYCODE_F2 -> {}
            KeyEvent.KEYCODE_F3 -> {}
            KeyEvent.KEYCODE_F4 -> {}
            KeyEvent.KEYCODE_DPAD_LEFT -> {}
            KeyEvent.KEYCODE_DPAD_RIGHT -> {}
            KeyEvent.KEYCODE_DPAD_DOWN -> {}
            KeyEvent.KEYCODE_DPAD_UP -> {}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginButton.setOnClickListener {
            Toast.makeText(context, "Login button clicked", Toast.LENGTH_SHORT).show()
            cicerone.router.navigateTo(ChatScreen())
        }
        super.onViewCreated(view, savedInstanceState)
    }
}