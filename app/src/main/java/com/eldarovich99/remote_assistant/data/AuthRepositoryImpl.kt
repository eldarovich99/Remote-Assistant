package com.eldarovich99.remote_assistant.data

import com.auth0.android.jwt.JWT
import com.eldarovich99.remote_assistant.data.api.LoginApi
import com.eldarovich99.remote_assistant.data.error_handling.ErrorHandler
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.AuthRepository
import com.eldarovich99.remote_assistant.domain.models.AuthInfo

class AuthRepositoryImpl: AuthRepository {
    val errorHandler = ErrorHandler()
    val api = NetworkClient.getApi(LoginApi::class.java)
    override suspend fun auth(body: AuthInfo): Result<JWT> {
        return errorHandler.executeSafeCall { api.auth(body) }
    }
}