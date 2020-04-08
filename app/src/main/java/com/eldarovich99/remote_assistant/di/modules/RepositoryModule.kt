package com.eldarovich99.remote_assistant.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.eldarovich99.remote_assistant.data.AccountsRepositoryImpl
import com.eldarovich99.remote_assistant.domain.accounts.AccountsRepository
import toothpick.config.Module

class RepositoryModule(private val context: Context) : Module() {
    init {
        // TODO just example
        val sharedPreferences = context.getSharedPreferences("app.prefs", MODE_PRIVATE)

        bind(SharedPreferences::class.java).toInstance(sharedPreferences)
        bind(AccountsRepository::class.java).to(AccountsRepositoryImpl::class.java).singleton()
    }
}