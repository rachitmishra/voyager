package com.voyager.utils

sealed class Result<T>(
    val _data: T? = null, val message: String? = null
) {
    data class Success<T>(val data: T?) : Result<T>(data)
    data class Error<T>(val error: Exception) : Result<T>(message = error.message)
    data class Loading<T>(val loading: Boolean = true) : Result<T>(null)
}
