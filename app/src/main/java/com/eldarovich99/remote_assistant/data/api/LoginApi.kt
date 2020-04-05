package com.eldarovich99.remote_assistant.data.api

import com.auth0.android.jwt.JWT
import com.eldarovich99.remote_assistant.domain.models.AuthInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST
    suspend fun auth(@Body u: AuthInfo): Response<JWT>
}