package com.eldarovich99.remote_assistant.presentation.view.chats

import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.accounts.AccountInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatsPresenter() {
    var view: ChatsFragment ?=null
    val interactor = AccountInteractor()

    fun onAttach(view: ChatsFragment){
        this.view = view
    }

    fun onDetach(){
        this.view = null
    }

    suspend fun getContacts() = withContext(Dispatchers.Main){
        val result = withContext(Dispatchers.IO){
            interactor.getContacts()
        }
        if (result is Result.Success){
            view?.updateContacts(result.data)
        }
        else{
            view?.showFailMessage()
        }
    }
}