package com.eldarovich99.remote_assistant.data

import com.auth0.android.jwt.JWT
import com.eldarovich99.remote_assistant.data.api.LoginApi
import com.eldarovich99.remote_assistant.data.error_handling.ErrorHandler
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.AuthRepository
import com.eldarovich99.remote_assistant.domain.models.AuthInfo
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val errorHandler: ErrorHandler): AuthRepository {
    val api = NetworkClient.getApi(LoginApi::class.java)
    override suspend fun auth(email: String, password: String): Result<JWT> {
        return errorHandler.executeSafeCall { api.auth(AuthInfo(email = email, password = password)) }
    }
}