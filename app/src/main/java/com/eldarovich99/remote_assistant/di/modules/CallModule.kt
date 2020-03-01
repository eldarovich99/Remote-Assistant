package com.eldarovich99.remote_assistant.di.modules

import com.eldarovich99.remote_assistant.presentation.view.call.CallFragment
import com.eldarovich99.remote_assistant.presentation.view.call.CallPresenter
import toothpick.config.Module

class CallModule(fragment: CallFragment): Module() {
    init {
        bind(CallPresenter::class.java).toInstance(CallPresenter(fragment))
    }
}