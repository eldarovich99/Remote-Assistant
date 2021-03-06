package com.eldarovich99.remote_assistant.presentation.view.chats

import android.text.Editable
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.accounts.AccountInteractor
import com.eldarovich99.remote_assistant.routing.CallScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ChatsPresenter @Inject constructor(val router: Router, private var interactor: AccountInteractor) {
    var view: ChatsView?=null
    fun onAttach(view: ChatsFragment) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }

    fun openCallScreen() {
        router.navigateTo(CallScreen())
    }

    suspend fun getContacts() = withContext(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) {
            interactor.getContacts()
        }
        if (result is Result.Success) {
            view?.updateContacts(result.data.records)
        } else {
            view?.showFailMessage()
        }
    }

    suspend fun getSearchResult(searchText: Editable) = withContext(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) {
            interactor.search(searchText.toString())
        }
        if (result is Result.Success) {
            view?.updateContacts(result.data.records)
        } else {
            view?.showFailMessage()
        }
    }
}