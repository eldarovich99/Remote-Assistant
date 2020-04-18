package com.eldarovich99.remote_assistant.presentation.view.chats

import com.eldarovich99.remote_assistant.domain.models.ContactFull


interface ChatsView {
    fun updateContacts(data: List<ContactFull>)
    fun showFailMessage()
}