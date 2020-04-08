package com.eldarovich99.remote_assistant.data.api

import com.eldarovich99.remote_assistant.domain.models.ContactBrief
import com.eldarovich99.remote_assistant.domain.models.ContactFull
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountsApi {
    @GET
    suspend fun getContacts() : Response<List<ContactBrief>>
    @GET
    suspend fun getSingleContact(@Query("id") id: String) : Response<List<ContactFull>>
    @GET
    suspend fun search(@Query("s") search: String) : Response<List<ContactFull>>
}