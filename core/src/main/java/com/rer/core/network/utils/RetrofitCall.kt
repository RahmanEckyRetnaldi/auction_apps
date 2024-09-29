package com.rer.core.network.utils

import android.util.Log
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import kotlin.coroutines.resume

suspend fun <R> Call<R>.withTimeOut(timeOut: Long = 30000L): DomainResult<R?> {
    return try {
        withTimeout(timeOut) {
            suspendCancellableCoroutine { continuation ->
                enqueue(object : Callback<R> {
                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        continuation.resume(response.parse())
                        Log.d("ECKY", "onResponse: $response")
                    }

                    override fun onFailure(call: Call<R>, t: Throwable) {
                        Log.e("WITH_TIME_OUT", "ON_FAILURE")
                        t.printStackTrace()
                        continuation.resume(DomainResult.Error(t.toError()))
                    }
                })
            }
        }
    } catch (e: Exception) {
        Log.e("WITH_TIME_OUT", "CATCH")
        e.printStackTrace()
        DomainResult.Error(e.toError())
    }
}

private fun <R> Response<R>.parse(): DomainResult<R?> {
    return if (isSuccessful) {
        DomainResult.Success(body())
    } else {
        DomainResult.Error(this.toError())
    }
}

fun <R> Response<R>.toError(): StatusResponse {
    val errorCode = code()
    return when {
        (errorCode in 500..599) -> {
            StatusResponse(
                isSuccess = isSuccessful,
                code = errorCode.toString(),
                message = "Server Error"
            )
        }

        (errorCode in 400..499) -> {
            if (errorCode == 403) {
                StatusResponse(
                    isSuccess = isSuccessful,
                    code = errorCode.toString(),
                    message = "Invalid Token"
                )
            } else {
                StatusResponse(
                    isSuccess = isSuccessful,
                    code = errorCode.toString(),
                    message = "Not Found Error"
                )
            }
        }

        else -> {
            StatusResponse(
                isSuccess = isSuccessful,
                code = errorCode.toString(),
                message = "Unknown Error"
            )
        }
    }
}

fun Throwable.toError(): StatusResponse {
    return when (this) {
        is SSLHandshakeException,
        is SocketTimeoutException,
        is ConnectException,
        is UnknownHostException -> {
            StatusResponse(
                isSuccess = false,
                code = "901",
                message = "Unreachable Exception"
            )
        }

        is TimeoutCancellationException -> {
            StatusResponse(
                isSuccess = false,
                code = "900",
                message = "Time Out Exception"
            )
        }

        else -> {
            StatusResponse(
                isSuccess = false,
                code = "999",
                message = "Unknown Exception"
            )
        }
    }
}