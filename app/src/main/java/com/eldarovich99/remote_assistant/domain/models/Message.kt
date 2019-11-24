package com.eldarovich99.remote_assistant.domain.models

class Message (type : String){
    companion object{
        const val OWNER = 1
        const val COMPANION = 2
    }
}