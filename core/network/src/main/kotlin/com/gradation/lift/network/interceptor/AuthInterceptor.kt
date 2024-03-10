package com.gradation.lift.network.interceptor

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.common.Constants.AUTHORIZATION_HEADER
import com.gradation.lift.network.common.Constants.BEARER
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * [AuthInterceptor]
 * 헤더에 토큰을 주입하여 전달하기 위한 Interceptor
 */
class AuthInterceptor @Inject constructor(
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val request = chain.request()
            chain.proceed(
                request.newBuilder()
                    .addHeader(
                        name = AUTHORIZATION_HEADER,
                        value = "${BEARER}${runBlocking { tokenDataStoreDataSource.accessToken.first() }}"
                    )
                    .build()

            )
        }
    }
}




