package com.eldarovich99.remote_assistant.domain.auth

import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.AuthResponse

interface AuthRepository {
    suspend fun auth(email: String, password: String): Result<AuthResponse>
}