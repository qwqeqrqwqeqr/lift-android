package com.gradation.lift.network.interceptor

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.Constants.DEFAULT_API_URL
import com.gradation.lift.network.common.Constants.UNAUTHORIZATION
import com.gradation.lift.network.dto.auth.RefreshResponseDto
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

        var isRefreshed = false
        if (response.code == UNAUTHORIZATION) {
            runBlocking {
                val result = refresh(tokenDataStoreDataSource, moshi)
                if (result.isSuccessful) {
                    tokenDataStoreDataSource.setAccessToken(result.body()?.data?.accessToken.toString())
                    isRefreshed = true
                }
            }
            if (isRefreshed) {
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
            } else {
                runBlocking {
                    tokenDataStoreDataSource.clearAll()
                }
            }
        }
        return null
    }


    private suspend fun refresh(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
        moshi: Moshi,
    ): retrofit2.Response<APIResultWrapper<RefreshResponseDto>> {
        val retrofit = Retrofit.Builder().baseUrl(DEFAULT_API_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        val service = retrofit.create(RefreshService::class.java)
        return service.refresh(
            Authorization = "${Constants.BEARER}${tokenDataStoreDataSource.refreshToken.first()}"
        )
    }
}