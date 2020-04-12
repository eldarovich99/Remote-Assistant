package com.eldarovich99.remote_assistant.data

import android.app.Application
import com.eldarovich99.remote_assistant.data.api.FileApi
import com.eldarovich99.remote_assistant.data.error_handling.ErrorHandler
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.files.FileRepository
import com.eldarovich99.remote_assistant.utils.FileWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(val appContext: Application,
                                             val errorHandler: ErrorHandler,
                                             val fileWriter: FileWriter
) : FileRepository {
    private val fileApi = NetworkClient.getApi(FileApi::class.java)

    override suspend fun downloadFile(url: String) : Result<ResponseBody> = withContext(Dispatchers.IO){
        errorHandler.executeSafeCall {
            fileApi.downloadFile(url)
        }
    }
}