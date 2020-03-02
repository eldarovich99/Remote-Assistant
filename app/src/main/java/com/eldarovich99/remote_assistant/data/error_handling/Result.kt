package com.eldarovich99.remote_assistant.data.error_handling

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val data: ErrorEntity) : Result<T>()
}