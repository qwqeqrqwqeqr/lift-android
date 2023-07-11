package com.gradation.lift.network.di

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject


class TestRetrofit @Inject constructor(
    private val mockWebServer: MockWebServer,
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi,
) {
    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
