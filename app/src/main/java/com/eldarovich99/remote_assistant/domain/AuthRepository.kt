package com.eldarovich99.remote_assistant.domain

import com.auth0.android.jwt.JWT
import com.eldarovich99.remote_assistant.data.error_handling.Result

interface AuthRepository {
    suspend fun auth(email: String, password: String): Result<JWT>
}