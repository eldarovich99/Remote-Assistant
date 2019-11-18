package com.eldarovich99.remote_assistant.di.modules

import androidx.appcompat.app.AppCompatActivity
import com.eldarovich99.remote_assistant.R
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.config.Module

class RouterModule(activity : AppCompatActivity) : Module(){
    var cicerone: Cicerone<Router> = Cicerone.create()
    var navigator = SupportAppNavigator(activity, R.id.fragment_host)

    fun getRouter() : Router = cicerone.router
    fun getNavigatorHolder() : NavigatorHolder = cicerone.navigatorHolder

}