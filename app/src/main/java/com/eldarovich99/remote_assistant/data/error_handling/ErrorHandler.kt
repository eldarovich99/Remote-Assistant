package com.eldarovich99.remote_assistant.data.error_handling

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandler {
    inline fun <T> executeSafeCall(getData: () -> Response<T>): Result<T> {
        return try {
            val response = getData.invoke()
            if (response.isSuccessful)
                Result.Success(response.body()!!)
            else {
                Result.Error(this.getError(response.code()))
            }
        } catch (e: Exception) {
            Result.Error(this.getError(e))
        }
    }

    fun getError(code: Int, throwable: Throwable? = null): ErrorEntity {
        return when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound(throwable)
            HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied(throwable)
            HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable(throwable)
            HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.InternalError(throwable)
            else -> ErrorEntity.Unknown(throwable)
        }
    }

    fun getError(throwable: Throwable?): ErrorEntity {
        return when (throwable) {
            is IOException -> {
                ErrorEntity.Network(throwable)
            }
            is HttpException -> {
                getError(throwable.code(), throwable)
            }
            else -> {
                ErrorEntity.Unknown(throwable)
            }

        }
    }
}