package com.jpgl.cryptocurrencies.utils

sealed class RequestState<T>(val data: T? = null, val message: String = "") {
    class Loading<T> : RequestState<T>()
    class Success<T>(response: T) : RequestState<T>(data = response)
    class Error<T>(errorMessage: String) : RequestState<T>(message = errorMessage)
}
