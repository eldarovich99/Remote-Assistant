package com.eldarovich99.remote_assistant.presentation.view.chats

import com.eldarovich99.remote_assistant.domain.models.ContactBrief

interface ChatsView {
    fun updateContacts(data: List<ContactBrief>)
    fun showFailMessage()
}