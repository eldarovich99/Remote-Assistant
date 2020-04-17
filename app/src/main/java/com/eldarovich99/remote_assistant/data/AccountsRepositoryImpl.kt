package com.eldarovich99.remote_assistant.data

import com.eldarovich99.remote_assistant.data.api.AccountsApi
import com.eldarovich99.remote_assistant.data.error_handling.ErrorHandler
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.accounts.AccountsRepository
import com.eldarovich99.remote_assistant.domain.models.ContactsResponse

class AccountsRepositoryImpl //@Inject constructor(val sharedPreferences: SharedPreferences) :
    :AccountsRepository {
    val errorHandler = ErrorHandler()
    val api = NetworkClient.getApi(AccountsApi::class.java)

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