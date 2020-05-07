package com.eldarovich99.remote_assistant.di.modules

import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.presentation.view.call.CallFragment
import com.eldarovich99.remote_assistant.presentation.view.call.UnsafeWebClient
import toothpick.config.Module

class CallModule(fragment: CallFragment): Module() {
    init {
        bind(CallFragment::class.java).toInstance(fragment)
        val layoutManager = LinearLayoutManager(fragment.context)
        layoutManager.stackFromEnd = true
        bind(RecyclerView.LayoutManager::class.java).toInstance(layoutManager)
        bind(WebViewClient::class.java).to(UnsafeWebClient::class.java)
        //bind(CallPresenter::class.java).toInstance(CallPresenter(fragment, FileInteractor()))
    }
}