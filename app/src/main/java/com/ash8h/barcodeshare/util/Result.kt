package com.ash8h.barcodeshare.util

sealed class Result<out T> {

    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val error: Throwable, val data: T? = null) : Result<T>()

    fun get(): T? = if (this is Success) data else null

    val isFailure: Boolean get() = this is Failure
    val isSuccess: Boolean get() = this is Success
}
