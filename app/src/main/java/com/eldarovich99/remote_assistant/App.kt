package com.eldarovich99.remote_assistant

import android.app.Application
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.di.modules.AppModule
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val appScope = Toothpick.openScope(Scopes.APP_SCOPE)
        appScope.installModules(AppModule(this))
        //appScope.installModules(RepositoryModule(this))
    }
}