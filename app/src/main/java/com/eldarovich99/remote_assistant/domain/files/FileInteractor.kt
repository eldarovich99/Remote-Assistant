package com.eldarovich99.remote_assistant.domain.files

import com.eldarovich99.remote_assistant.data.FileRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FileInteractor @Inject constructor(private val repository: FileRepositoryImpl) {
    suspend fun downloadFile(url: String)= withContext(Dispatchers.IO){
        repository.downloadFile(url)
    }
}