package com.eldarovich99.remote_assistant

import android.app.Application
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.di.modules.AppModule
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val appScope = Toothpick.openScope(Scopes.APP_SCOPE)
        appScope.installModules(AppModule(this))
        Toothpick.setConfiguration(if (BuildConfig.DEBUG) Configuration.forDevelopment() else Configuration.forProduction())
        //appScope.installModules(RepositoryModule(this))
    }
}