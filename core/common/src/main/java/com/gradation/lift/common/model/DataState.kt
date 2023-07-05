package com.gradation.lift.common.model

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Fail(val message: String) : DataState<Nothing>()
}




