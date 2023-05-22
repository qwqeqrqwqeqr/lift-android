package com.gradation.lift.network.common

import com.gradation.lift.common.di.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


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


