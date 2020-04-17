package com.eldarovich99.remote_assistant.domain.accounts

import com.eldarovich99.remote_assistant.data.AccountsRepositoryImpl
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.ContactsResponse

class AccountInteractor {
    val repository = AccountsRepositoryImpl()
    suspend fun getContacts(): Result<ContactsResponse> {
        return repository.getContacts()
    }

    suspend fun getSingleContact(id: String): Result<ContactsResponse> {
        return repository.getSingleContact(id)
    }

    suspend fun search(search: String): Result<ContactsResponse> {
        return repository.search(search)
    }
}