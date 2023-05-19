package com.gradation.lift.common.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class DataState<out T> {
    data class Success<T>(val data: T?) : DataState<T>()
    data class Error(val message: String? = null) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}




