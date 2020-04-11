package com.eldarovich99.remote_assistant.presentation.view.chats

import com.eldarovich99.remote_assistant.routing.CallScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ChatsPresenter @Inject constructor(val router: Router) {
    var view: ChatsView?=null

    fun onAttach(view: ChatsView){
        this.view = view
    }

    fun onDetach(){
        this.view = null
    }

    fun openCallScreen(){
        router.navigateTo(CallScreen())
    }
}