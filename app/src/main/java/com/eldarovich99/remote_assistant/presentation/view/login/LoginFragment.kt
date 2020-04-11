package com.eldarovich99.remote_assistant.presentation.view.login

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.di.modules.LoginModule
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.routing.ChatScreen
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import toothpick.ktp.KTP
import javax.inject.Inject

class LoginFragment : BaseFragment() {
    @Inject
    lateinit var presenter: LoginPresenter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        KTP.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE, Scopes.LOGIN_SCOPE)
            .installModules(LoginModule(this)).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onDestroy() {
        KTP.closeScope(Scopes.LOGIN_SCOPE)
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginButton.setOnClickListener {
            CoroutineScope(uiScope).launch {
                presenter.auth(loginEditText.text.toString(), passwordEditText.text.toString())
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun openChatScreen(){
        router.newRootScreen(ChatScreen())
    }
}