package com.eldarovich99.remote_assistant.presentation.view.login

import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.AuthInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginPresenter @Inject constructor(val view: LoginFragment, val interactor: AuthInteractor) {
    //val interactor = AuthInteractor(AuthRepositoryImpl())
    suspend fun auth(email: String, password: String) = withContext(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) {
            interactor.auth(email, password)
        }
        if (result is Result.Success) {
            view.openChatScreen()
            //router.newRootScreen(ChatScreen())
        } else {
            view.showErrorMessage()
        }

    }
}