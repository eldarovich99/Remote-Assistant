package com.eldarovich99.remote_assistant.di.modules

import com.eldarovich99.remote_assistant.data.AccountsRepositoryImpl
import com.eldarovich99.remote_assistant.data.NetworkClient
import com.eldarovich99.remote_assistant.data.api.AccountsApi
import com.eldarovich99.remote_assistant.data.error_handling.ErrorHandler
import com.eldarovich99.remote_assistant.domain.accounts.AccountsRepository
import toothpick.config.Module

class ChatsModule: Module() {
    init {
        bind(AccountsRepository::class.java).to(AccountsRepositoryImpl::class.java)
        bind(ErrorHandler::class.java).to(ErrorHandler::class.java)
        bind(AccountsApi::class.java).toInstance(NetworkClient.getApi(AccountsApi::class.java))
    }
}