package com.gradation.lift.network.interceptor

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants.APPLICATION_JSON
import com.gradation.lift.network.common.Constants.AUTHORIZATION_HEADER
import com.gradation.lift.network.common.Constants.BEARER
import com.gradation.lift.network.common.Constants.DEFAULT_API_URL
import com.gradation.lift.network.common.Constants.UNAUTHORIZATION
import com.gradation.lift.network.dto.auth.RefreshResponseDto
import com.gradation.lift.network.service.RefreshService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * [AuthAuthenticator]
 * 401 에러를 처리하기 위한 Authenticator
 * refresh 를 실패할 경우 저장소에 저장되어 있는 토큰 데이터를 전부 초기화 함
 */
class AuthAuthenticator @Inject constructor(
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {

        var isRefreshed = false
        if (response.code == UNAUTHORIZATION) {
            runBlocking {
                val result = refresh(tokenDataStoreDataSource)
                if (result.isSuccessful) {
                    tokenDataStoreDataSource.setAccessToken(result.body()?.data?.accessToken.toString())
                    isRefreshed = true
                }
            }
            if (isRefreshed) {
                return response.request
                    .newBuilder()
                    .removeHeader(AUTHORIZATION_HEADER)
                    .addHeader(
                        name = AUTHORIZATION_HEADER,
                        value = "$BEARER${
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


    /**
     * [refresh]
     * Refresh Token 을 불러와 Access Token을 갱신합니다.
     */
    private suspend fun refresh(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): retrofit2.Response<APIResultWrapper<RefreshResponseDto>> {
        val retrofit = Retrofit.Builder().baseUrl(DEFAULT_API_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .addConverterFactory(
                with(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }) { asConverterFactory(APPLICATION_JSON.toMediaType()) }
            ).build()
        val service = retrofit.create(RefreshService::class.java)
        return service.refresh(
            Authorization = "${BEARER}${tokenDataStoreDataSource.refreshToken.first()}"
        )
    }
}