package com.gradation.lift.domain.model.common

sealed class DataState<out T> {
    data class Success<T>(val data: T?) : DataState<T>()
    data class Error(val message: String? = null) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}




