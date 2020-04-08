package com.eldarovich99.remote_assistant.domain.accounts

import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.models.ContactBrief
import com.eldarovich99.remote_assistant.domain.models.ContactFull

interface AccountsRepository {
    suspend fun getContacts() : Result<List<ContactBrief>>
    suspend fun getSingleContact(id: String) : Result<List<ContactFull>>
    suspend fun search(search: String) : Result<List<ContactFull>>
}