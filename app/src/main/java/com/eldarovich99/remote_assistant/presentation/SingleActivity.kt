package com.eldarovich99.remote_assistant.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.eldarovich99.remote_assistant.IOnBackPressed
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.di.modules.RouterModule
import com.eldarovich99.remote_assistant.presentation.view.LoginFragment
import toothpick.Toothpick

class SingleActivity : AppCompatActivity(), IOnBackPressed{

    override fun onCreate(savedInstanceState: Bundle?) {
        enableFullScreenMode()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        val appScope = Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE)
        appScope.installModules(RouterModule(this))

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_host, LoginFragment())
            .commit()
    }

    fun enableFullScreenMode(){
        val winParams = window?.attributes
        winParams?.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.attributes = winParams
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        val fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size-1] as BaseFragment
        fragment.dispatchKeyEvent(event)
        return super.dispatchKeyEvent(event)
    }

    override fun onDestroy() {
        Toothpick.closeScope(Scopes.ACTIVITY_SCOPE)
        super.onDestroy()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size-1]
        if (fragment is IOnBackPressed){
            fragment.onBackPressed()
        }
        else
            super.onBackPressed()
    }
}
