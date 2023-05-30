package com.gradation.lift.domain.model.common

sealed class DataState<out T> {
    data class Success<T>(val data: T?) : DataState<T>()
    data class Fail(val message: String) : DataState<Nothing>()
    data class Error(val message: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}




