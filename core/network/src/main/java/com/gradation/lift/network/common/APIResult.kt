package com.gradation.lift.network.common

import okio.IOException
import retrofit2.HttpException
import retrofit2.Response


data class APIResult<out T : Any>(
    val status: Boolean,
    val message: String,
    val data: T?
)

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T?, val message: String) : NetworkResult<T>()
    data class Fail<out T : Any>(val data: T?, val message: String,val code:Int) : NetworkResult<T>()
    data class Error(val exception: Throwable? = null) : NetworkResult<Nothing>()
}



suspend fun <T : Any> networkResultHandler(call: suspend () -> Response<APIResult<T>>): NetworkResult<T> {
    return try {
        val response = call.invoke()
        //200~299
        if (response.isSuccessful) {
            NetworkResult.Success(
                data = response.body()?.data, message = response.body()?.message
                    ?: "empty"
            )
        } else {
            NetworkResult.Fail(
                data = response.body()?.data, message = response.body()?.message
                    ?: "empty",code=response.code()
            )
        }
        //TODO change the null message


    } catch (e: HttpException) {
        NetworkResult.Error(e)
    } catch (e: Throwable) {
        NetworkResult.Error(e)
    }
}