package com.rer.core.network.utils

sealed class DomainResult<out T> {

    data class Success<T>(
        val data: T
    ) : DomainResult<T>()

    data class Error(
        val statusResponse: StatusResponse? = null
    ) : DomainResult<Nothing>()

    inline fun <V> fold(failure: (StatusResponse?) -> V, success: (T) -> V): V = when (this) {
        is Error -> failure(statusResponse)
        is Success -> success(data)
    }
}