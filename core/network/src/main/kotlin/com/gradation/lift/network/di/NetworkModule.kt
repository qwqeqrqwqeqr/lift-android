package com.gradation.lift.network.di

import android.content.Context
import android.net.ConnectivityManager
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.di.annotation.AuthHttpClient
import com.gradation.lift.network.di.annotation.AuthNetworkInterceptor
import com.gradation.lift.network.di.annotation.AuthRetrofit
import com.gradation.lift.network.di.annotation.DefaultHttpClient
import com.gradation.lift.network.di.annotation.DefaultRetrofit
import com.gradation.lift.network.di.annotation.NetworkConnectivityInterceptor
import com.gradation.lift.network.di.annotation.RetryNetworkInterceptor
import com.gradation.lift.network.interceptor.AuthAuthenticator
import com.gradation.lift.network.service.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @DefaultHttpClient
    @Provides
    @Singleton
    fun provideHttpClient(
        @RetryNetworkInterceptor retryInterceptor: Interceptor,
        @NetworkConnectivityInterceptor connectivityInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(retryInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @AuthHttpClient
    @Provides
    @Singleton
    fun provideAuthHttpClient(
        @RetryNetworkInterceptor retryInterceptor: Interceptor,
        @AuthNetworkInterceptor authInterceptor: Interceptor,
        @NetworkConnectivityInterceptor connectivityInterceptor: Interceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(retryInterceptor)
            .authenticator(authAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


    @DefaultRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        @DefaultHttpClient okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.DEFAULT_API_URL)
            .client(okHttpClient)
            .addConverterFactory(
                with(Json {
                    ignoreUnknownKeys = true // 느슨한 직렬화 처리
                    coerceInputValues = true //null 일 경우 기본값 대입
                }) { asConverterFactory("application/json".toMediaType()) }
            )
            .build()
    }

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        @AuthHttpClient okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.DEFAULT_API_URL)
            .client(okHttpClient)
            .addConverterFactory(
                with(Json {
                    ignoreUnknownKeys = true // 느슨한 직렬화 처리
                    coerceInputValues = true //null 일 경우 기본값 대입
                }) { asConverterFactory("application/json".toMediaType()) }
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context,
    ): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

}
