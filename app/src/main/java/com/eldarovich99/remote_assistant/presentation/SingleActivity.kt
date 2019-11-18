package com.eldarovich99.remote_assistant.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.di.modules.RouterModule
import toothpick.Toothpick

class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appScope = Toothpick.openScope(Scopes.ACTIVITY_SCOPE)
        appScope.installModules(RouterModule(this))
    }
}
