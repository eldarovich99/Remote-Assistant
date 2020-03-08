package com.eldarovich99.remote_assistant.presentation.view.call

import com.eldarovich99.remote_assistant.domain.FileInteractor
import javax.inject.Inject

class CallPresenter @Inject constructor(val view: CallFragment, val interactor: FileInteractor) {
    fun downloadFile(link: String){
        interactor.downloadFile(link)
    }
}