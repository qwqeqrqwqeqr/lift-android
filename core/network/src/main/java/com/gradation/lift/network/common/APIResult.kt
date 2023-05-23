package com.gradation.lift.network.common


data class APIResultWrapper<out T : Any>(
    val status: Boolean,
    val message: String,
    val data: T?
)

sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(val data: T?, val message: String) : APIResult<T>()
    data class Fail<out T : Any>(val data: T?, val message: String) : APIResult<T>()
    data class Error(val exception: Throwable? = null) : APIResult<Nothing>()
    object Loading : APIResult<Nothing>()
}


