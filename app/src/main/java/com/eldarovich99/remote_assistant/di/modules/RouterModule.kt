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
    val cicerone : Cicerone<Router> by lazy{ Cicerone.create(router) }
    val router : Router by lazy {Router()}
    init {
        bind(Cicerone::class.java).toInstance(cicerone)

        val navigator = SingleNavigator(activity, R.id.fragment_host)
        bind(Navigator::class.java).toInstance(navigator)
        bind(Router::class.java).toInstance(router)
    }
    fun getNavigatorHolder() : NavigatorHolder = cicerone.navigatorHolder

}