package com.eldarovich99.remote_assistant.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.domain.UserRepository
import toothpick.Toothpick
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, Toothpick.openScope(Scopes.APP_SCOPE))
    }
}