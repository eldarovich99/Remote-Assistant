package com.eldarovich99.remote_assistant.presentation

import android.view.ContextMenu
import android.view.View
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.domain.UserRepository
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

open class BaseFragment : Fragment(){
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        Toothpick.inject(this, Toothpick.openScope(Scopes.APP_SCOPE))
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResume() {
        cicerone.navigatorHolder.setNavigator(navigator)
        super.onResume()
    }
}