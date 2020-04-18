package com.eldarovich99.remote_assistant.data

import com.eldarovich99.remote_assistant.data.api.AccountsApi
import com.eldarovich99.remote_assistant.data.error_handling.ErrorHandler
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.accounts.AccountsRepository
import com.eldarovich99.remote_assistant.domain.models.ContactBrief
import com.eldarovich99.remote_assistant.domain.models.ContactFull
import com.eldarovich99.remote_assistant.domain.models.ContactsResponse
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val api: AccountsApi
)
    :AccountsRepository {

    override suspend fun getContacts(): Result<ContactsResponse> {
        return errorHandler.executeSafeCall { api.getContacts() }
    }

    override suspend fun getSingleContact(id: String): Result<ContactsResponse> {
        return errorHandler.executeSafeCall { api.getSingleContact(id) }
    }

    override suspend fun search(search: String): Result<ContactsResponse> {
        return errorHandler.executeSafeCall { api.search(search) }
    }
}