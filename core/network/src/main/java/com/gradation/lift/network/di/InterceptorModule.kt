package com.gradation.lift.network.di

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.handler.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    @Singleton
    fun provideAuthHeaderInterceptor(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): Interceptor = AuthInterceptor(tokenDataStoreDataSource)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
}