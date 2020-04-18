package com.eldarovich99.remote_assistant.data.api

import com.eldarovich99.remote_assistant.domain.models.AuthInfo
import com.eldarovich99.remote_assistant.domain.models.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/api/login.php")
    suspend fun auth(@Body u: AuthInfo): Response<AuthResponse>
}