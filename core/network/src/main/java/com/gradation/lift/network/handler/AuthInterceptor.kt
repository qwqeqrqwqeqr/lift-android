package com.gradation.lift.network.handler

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.common.Constants.BEARER
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val request = chain.request()
            chain.proceed(
                request.newBuilder()
                    .addHeader(
                        "Authorization",
                        "${BEARER}${
                            runBlocking {
                                tokenDataStoreDataSource.accessToken.first()
                            }
                        }"
                    )
                    .build()

            )
        }
    }
}




