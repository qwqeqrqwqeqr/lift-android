package com.gradation.lift.network.common

import retrofit2.Response


data class Result<out T : Any>(
    val status: Boolean,
    val message: String,
    val data: T?
)

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : NetworkResult<T>()
    data class Fail<out T : Any>(
        val message:  String
    ) : NetworkResult<T>()
    data class Error(val exception: Throwable? = null) : NetworkResult<Nothing>()
}


suspend fun <T : Any> networkResultHandler(call: suspend () -> Response<Result<T>>): NetworkResult<T> {
    return try {
        val response = call.invoke()

        if (response.isSuccessful) {
            NetworkResult.Success(data = response.body()?.data)
        } else {
            NetworkResult.Fail(response.body()?.message ?: "Error")
        }

    } catch (e: Exception) {
        NetworkResult.Error()
    }
}