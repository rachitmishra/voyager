package com.voyager.utils

sealed interface Result<T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val error: Exception = Exception()) : Result<T> {
        val msg = error.message ?: ""
    }
}
