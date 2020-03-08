package com.eldarovich99.remote_assistant.domain

import com.eldarovich99.remote_assistant.data.FileRepositoryImpl
import javax.inject.Inject

class FileInteractor @Inject constructor(val repository: FileRepositoryImpl) {
    fun downloadFile(url: String){
        repository.downloadFile(url)
    }
}