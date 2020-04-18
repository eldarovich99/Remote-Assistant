package com.eldarovich99.remote_assistant.domain.accounts

import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.ContactBrief
import com.eldarovich99.remote_assistant.domain.models.ContactFull
import javax.inject.Inject

class AccountInteractor @Inject constructor(private val repository: AccountsRepository) {
    suspend fun getContacts(): Result<List<ContactBrief>> {
        return repository.getContacts()
    }

    suspend fun getSingleContact(id: String): Result<ContactsResponse> {
        return repository.getSingleContact(id)
    }

    suspend fun search(search: String): Result<ContactsResponse> {
        return repository.search(search)
    }
}