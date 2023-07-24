package com.gradation.lift.network.di

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.di.annotation.AuthHttpClient
import com.gradation.lift.network.di.annotation.AuthRetrofit
import com.gradation.lift.network.di.annotation.DefaultHttpClient
import com.gradation.lift.network.di.annotation.DefaultRetrofit
import com.gradation.lift.network.handler.AuthAuthenticator
import com.gradation.lift.network.handler.AuthInterceptor
import com.gradation.lift.network.handler.RetryInterceptor
import com.gradation.lift.network.service.*
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @DefaultHttpClient
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(RetryInterceptor())
            .build()
    }

    @AuthHttpClient
    @Provides
    @Singleton
    fun provideAuthHttpClient(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
        moshi: Moshi,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(
                AuthInterceptor(tokenDataStoreDataSource = tokenDataStoreDataSource)
            )
            .addInterceptor(RetryInterceptor())
            .authenticator(
                AuthAuthenticator(
                    tokenDataStoreDataSource = tokenDataStoreDataSource,
                    moshi = moshi
                )
            )
            .build()
    }


    @DefaultRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        @DefaultHttpClient okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.DEFAULT_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        @AuthHttpClient okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.DEFAULT_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


}
