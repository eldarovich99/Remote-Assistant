package com.eldarovich99.remote_assistant.domain

import com.eldarovich99.remote_assistant.domain.models.User

interface UserRepository {
    fun getUser(): User
    fun saveUser(user: User)
}