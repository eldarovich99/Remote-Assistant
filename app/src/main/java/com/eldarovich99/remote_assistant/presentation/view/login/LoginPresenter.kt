package com.eldarovich99.remote_assistant.presentation.view.login

import com.eldarovich99.remote_assistant.domain.auth.AuthInteractor
import com.eldarovich99.remote_assistant.routing.ChatScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LoginPresenter @Inject constructor(val view: LoginFragment, val interactor: AuthInteractor, val router: Router) {
    suspend fun auth(email: String, password: String) = withContext(Dispatchers.Main) {
        router.navigateTo(ChatScreen())
        /*val result = withContext(Dispatchers.IO) {
            interactor.auth(email, password)
        }
        if (result is Result.Success) {
            router.navigateTo(ChatScreen())
            //view.openChatScreen()
        } else {
            view.showErrorMessage()
        }*/
    }
}