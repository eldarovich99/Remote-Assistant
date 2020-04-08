package com.eldarovich99.remote_assistant.domain.accounts

import com.eldarovich99.remote_assistant.data.AccountsRepositoryImpl
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.ContactBrief
import com.eldarovich99.remote_assistant.domain.models.ContactFull

class AccountInteractor {
    val repository = AccountsRepositoryImpl()
    suspend fun getContacts(): Result<List<ContactBrief>> {
        return repository.getContacts()
    }

    suspend fun getSingleContact(id: String): Result<List<ContactFull>> {
        return repository.getSingleContact(id)
    }

    suspend fun search(search: String): Result<List<ContactFull>> {
        return repository.search(search)
    }
}