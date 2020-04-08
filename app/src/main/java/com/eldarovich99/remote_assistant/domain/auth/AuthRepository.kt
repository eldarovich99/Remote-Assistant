package com.eldarovich99.remote_assistant.domain.auth

import com.auth0.android.jwt.JWT
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.AuthInfo

interface AuthRepository {
    suspend fun auth(body: AuthInfo): Result<JWT>
}