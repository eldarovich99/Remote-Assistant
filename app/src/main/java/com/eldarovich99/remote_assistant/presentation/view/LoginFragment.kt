package com.eldarovich99.remote_assistant.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.presentation.QrReaderFragment
import com.eldarovich99.remote_assistant.routing.ChatScreen
import com.eldarovich99.remote_assistant.routing.RestorePasswordScreen
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override suspend fun dispatchKeyEvent(event: KeyEvent?){
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
            router.newRootScreen(ChatScreen())
        }
        forgetPasswordButton.setOnClickListener {
            router.navigateTo(RestorePasswordScreen(

            ))
        }
        /*qrImageView.setOnClickListener {
            QrReaderFragment.start(this, router)
        }*/
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> { QrReaderFragment.onRequestPermissionsResult(requestCode, grantResults, router)
            }
        }
    }
}