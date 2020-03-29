package com.ash8h.barcodeshare.util

sealed class RemoteData<out T> {
    object Loading : RemoteData<Nothing>()
    data class Success<T>(val data: T) : RemoteData<T>()
    data class Failure<T>(val error: Throwable, val data: T? = null) : RemoteData<T>()

    fun get(): T? = if (this is Success) data else null

    val isLoading: Boolean get() = this is Loading
    val isFailure: Boolean get() = this is Failure
    val isSuccess: Boolean get() = this is Success
}

val RemoteData<*>?.isLoading: Boolean
    get() = this != null && this.isLoading
