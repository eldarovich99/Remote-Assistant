package com.eldarovich99.remote_assistant.data

import android.content.SharedPreferences
import com.eldarovich99.remote_assistant.domain.UserRepository
import com.eldarovich99.remote_assistant.domain.models.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val sharedPreferences: SharedPreferences) : UserRepository {
    override fun getUser(): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}