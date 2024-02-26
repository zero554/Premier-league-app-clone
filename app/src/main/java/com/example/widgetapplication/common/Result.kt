package com.example.widgetapplication.common

import kotlinx.coroutines.CancellationException

sealed class Result<T> {

    class Success<T> (
        val data: T? = null,
    ): Result<T>()

    class Error<T> (val throwable: Throwable) : Result<T>()

    inline fun doOnError(block: (Throwable) -> Unit) = this.also {
        if (this is Error) block(throwable)
    }

    inline fun doOnSuccess(block: (T?) -> Unit) = this.also {
        if (this is Success) block(data)
    }

    companion object {
        fun <T> wrap(block: () -> T): Result<T> =
            try {
                Success(block())
            } catch (e: CancellationException) {
                throw e // throw exception to stop coroutine
            } catch (e: Throwable) {
                Error(e)
            }
    }
}