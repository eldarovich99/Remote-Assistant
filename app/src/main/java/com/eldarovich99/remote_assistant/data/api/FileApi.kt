package com.eldarovich99.remote_assistant.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface FileApi {
    @Streaming
    @GET("{link}")
    suspend fun downloadFile(@Path("link")link: String) : Response<ResponseBody>
}