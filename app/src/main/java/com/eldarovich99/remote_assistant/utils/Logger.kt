package com.eldarovich99.remote_assistant.utils

object Logger {
    fun log(obj : Any, message : String, feature : String?=null){
        println("Log: ${obj.javaClass.name}, $message, ${feature ?: ""}")
    }
}