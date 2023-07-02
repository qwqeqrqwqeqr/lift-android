package com.gradation.lift.network.di

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.handler.DefaultNetworkResultHandler
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.AuthService
import com.gradation.lift.network.service.RefreshService
import com.gradation.lift.network.service.RoutineService
import com.gradation.lift.network.service.WorkService
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

    @Provides
    @Singleton
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }


    @Provides
    @Singleton
    fun provideNetworkResultHandler(dispatcherProvider: DispatcherProvider): NetworkResultHandler =
        DefaultNetworkResultHandler(dispatcherProvider)





    @Provides
    @Singleton
    fun provideWorkService(retrofit: Retrofit): WorkService =
        retrofit.create(WorkService::class.java)

    @Provides
    @Singleton
    fun provideRoutineService(retrofit: Retrofit): RoutineService =
        retrofit.create(RoutineService::class.java)


    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)


    @Provides
    @Singleton
    fun provideRefreshService(retrofit: Retrofit): RefreshService =
        retrofit.create(RefreshService::class.java)




}