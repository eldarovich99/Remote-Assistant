package com.eldarovich99.remote_assistant.domain.accounts

import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.ContactsResponse

interface AccountsRepository {
    suspend fun getContacts() : Result<ContactsResponse>
    suspend fun getSingleContact(id: String) : Result<ContactsResponse>
    suspend fun search(search: String) : Result<ContactsResponse>
}