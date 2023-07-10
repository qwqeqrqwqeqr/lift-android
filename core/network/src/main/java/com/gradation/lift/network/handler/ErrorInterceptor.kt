package com.gradation.lift.network.handler

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ErrorInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val newRequest = request().newBuilder().build()
            proceed(newRequest)
        }
    }

}