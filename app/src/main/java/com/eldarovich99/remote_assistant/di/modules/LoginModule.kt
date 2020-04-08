package com.eldarovich99.remote_assistant.di.modules

import com.eldarovich99.remote_assistant.presentation.view.login.LoginFragment
import toothpick.config.Module

class LoginModule (val view: LoginFragment): Module(){
    init {
        bind(LoginFragment::class.java).toInstance(view)
    }
}