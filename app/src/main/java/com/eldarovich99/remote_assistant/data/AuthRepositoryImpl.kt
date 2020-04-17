package com.eldarovich99.remote_assistant.data

import com.eldarovich99.remote_assistant.data.api.LoginApi
import com.eldarovich99.remote_assistant.data.error_handling.ErrorHandler
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.auth.AuthRepository
import com.eldarovich99.remote_assistant.domain.models.AuthInfo
import com.eldarovich99.remote_assistant.domain.models.AuthResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor():
    AuthRepository {
    val errorHandler = ErrorHandler()
    val api = NetworkClient.getApi(LoginApi::class.java)
    override suspend fun auth(email: String, password: String): Result<AuthResponse> {
        return errorHandler.executeSafeCall { api.auth(AuthInfo(email = email, password = password)) }
    }
}