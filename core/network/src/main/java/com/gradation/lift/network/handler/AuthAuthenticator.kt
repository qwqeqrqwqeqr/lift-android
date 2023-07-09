package com.gradation.lift.network.handler

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.Constants.BASE_URL
import com.gradation.lift.network.common.Constants.UNAUTHORIZATION
import com.gradation.lift.network.service.RefreshService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
    private val moshi: Moshi,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {


        if (response.code == UNAUTHORIZATION) {
            runBlocking {
                refresh(tokenDataStoreDataSource, moshi)
            }
            return response.request
                .newBuilder()
                .removeHeader("Authorization")
                .addHeader(
                    "Authorization",
                    "${Constants.BEARER}${
                        runBlocking {
                            tokenDataStoreDataSource.accessToken.first()
                        }
                    }"
                ).build()
        }
        return null

    }


    private suspend fun refresh(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
        moshi: Moshi,
    ) {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BASIC
            }).build())
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        val service = retrofit.create(RefreshService::class.java)
        service.refresh(authorization = "${Constants.BEARER}${tokenDataStoreDataSource.refreshToken.first()}")
    }
}