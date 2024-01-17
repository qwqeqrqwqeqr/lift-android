package com.gradation.lift.network.di

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.di.annotation.AuthNetworkInterceptor
import com.gradation.lift.network.di.annotation.RetryNetworkInterceptor
import com.gradation.lift.network.interceptor.AuthAuthenticator
import com.gradation.lift.network.interceptor.AuthInterceptor
import com.gradation.lift.network.interceptor.RetryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Singleton
    @Provides
    fun provideAuthAuthenticator(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): AuthAuthenticator =
        AuthAuthenticator(
            tokenDataStoreDataSource = tokenDataStoreDataSource,
        )


    @AuthNetworkInterceptor
    @Singleton
    @Provides
    fun provideAuthInterceptor(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): Interceptor =
        AuthInterceptor(tokenDataStoreDataSource)


    @RetryNetworkInterceptor
    @Singleton
    @Provides
    fun provideRetryInterceptor(): Interceptor = RetryInterceptor()
}