package com.example.propertyapp.common

import com.example.propertyapp.common.Status.*

enum class Status {
    SUCCESS, ERROR, IDLE, LOADING
}

data class RequestState<out T>(
    val status: Status,
    val data: T?,
) {
    companion object {
        fun <T> success(data: T?): RequestState<T> =
            RequestState(SUCCESS, data)

        fun <T> error(throwable: Throwable): RequestState<T> =
            RequestState(ERROR, null)

        fun <T> loading(): RequestState<T> =
            RequestState(LOADING, null)

        fun <T> idle(): RequestState<T> =
            RequestState(IDLE, null)
    }
}
