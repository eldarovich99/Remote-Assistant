package com.eldarovich99.remote_assistant.domain.files

import com.eldarovich99.remote_assistant.data.error_handling.Result
import okhttp3.ResponseBody

interface FileRepository {
    suspend fun downloadFile(url: String) : Result<ResponseBody>
}