package com.eldarovich99.remote_assistant.domain

interface FileRepository {
    fun downloadFile(url: String)
}