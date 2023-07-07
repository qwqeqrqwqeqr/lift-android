package com.gradation.lift.network.di

import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.network.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    @Singleton
    fun provideAuthHeaderInterceptor(userDataStoreDataSource: UserDataStoreDataSource): Interceptor =
        Interceptor { chain: Interceptor.Chain ->
            with(chain) {
                val request = chain.request()

                val builder = request.newBuilder()
                    .addHeader(
                        "Authorization",
                        "${userDataStoreDataSource.accessToken}"
                    )
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Accept", "application/json; charset=utf-8")
                proceed(builder.build()).apply {
                    if (this.code == 401 || this.code == 403) {
                        this.body?.close()
                        val newRequest = request.newBuilder().addHeader(
                            "Authorization",
                            "${Constants.BEARER}${userDataStoreDataSource.refreshToken}"
                        )
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Accept", "application/json; charset=utf-8")
                        chain.proceed(newRequest.build())
                    }
                }
            }
        }


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

}