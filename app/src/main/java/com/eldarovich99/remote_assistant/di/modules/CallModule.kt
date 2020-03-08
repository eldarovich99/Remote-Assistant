package com.eldarovich99.remote_assistant.di.modules

import com.eldarovich99.remote_assistant.presentation.view.call.CallFragment
import toothpick.config.Module

class CallModule(fragment: CallFragment): Module() {
    init {
        bind(CallFragment::class.java).toInstance(fragment)
        //bind(CallPresenter::class.java).toInstance(CallPresenter(fragment, FileInteractor()))
    }
}