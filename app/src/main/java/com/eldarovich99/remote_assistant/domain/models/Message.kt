package com.eldarovich99.remote_assistant.domain.models

class Message (val type : Int){
    companion object{
        const val OWNER = 1
        const val COMPANION = 2
        const val OWNER_FILE = 3
        const val COMPANION_FILE = 4
    }
}