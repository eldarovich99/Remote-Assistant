package com.eldarovich99.remote_assistant.di.modules

import androidx.appcompat.app.AppCompatActivity
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.routing.SingleNavigator
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class RouterModule(activity : AppCompatActivity) : Module(){
    val cicerone : Cicerone<Router> by lazy{ Cicerone.create() }
    init {
        bind(Cicerone::class.java).toInstance(cicerone)

        val navigator = SingleNavigator(activity, R.id.fragment_host)
        bind(Navigator::class.java).toInstance(navigator)
    }

    fun getRouter() : Router = cicerone.router
    fun getNavigatorHolder() : NavigatorHolder = cicerone.navigatorHolder

}