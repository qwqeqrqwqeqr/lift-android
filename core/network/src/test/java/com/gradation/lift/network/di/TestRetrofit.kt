package com.gradation.lift.network.di


import com.gradation.lift.network.common.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


class TestRetrofit(
    private val mockWebServer: MockWebServer,
) {
    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                    .readTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                    .writeTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS).build()
            )
            .addConverterFactory(
                with(Json {
                    ignoreUnknownKeys = true // 느슨한 직렬화 처리
                    coerceInputValues = true //null 일 경우 기본값 대입
                }) { asConverterFactory("application/json".toMediaType()) }
            )
            .build()
    }
}


