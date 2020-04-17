package com.eldarovich99.remote_assistant.domain.auth

import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.AuthResponse
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val repository: AuthRepository) {
    suspend fun auth(email: String, password: String): Result<AuthResponse>{
        return repository.auth(email, password)
    }
}