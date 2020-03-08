package com.eldarovich99.remote_assistant.data

import android.app.Application
import com.eldarovich99.remote_assistant.domain.FileRepository
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(val appContext: Application) : FileRepository {
    override fun downloadFile(url: String) {

    }
}