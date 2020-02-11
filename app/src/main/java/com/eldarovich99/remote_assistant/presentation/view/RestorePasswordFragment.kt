package com.eldarovich99.remote_assistant.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import toothpick.Toothpick

class RestorePasswordFragment : BaseFragment() {
    override suspend fun dispatchKeyEvent(event: KeyEvent?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restore, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE))
        super.onCreate(savedInstanceState)
    }
}