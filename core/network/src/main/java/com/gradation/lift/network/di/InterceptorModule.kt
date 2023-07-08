package com.gradation.lift.network.di

import android.os.SystemClock.sleep
import android.util.Log
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.Constants.UNAUTHORIZATION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Delay
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
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
                var response: Response = chain.proceed(
                    request.newBuilder().addHeader(
                        "Authorization",
                        "${Constants.BEARER}${userDataStoreDataSource.accessToken}"
                    ).build()
                )

                var isSuccess = response.isSuccessful
                var tryCount = 0
                val isRefreshed = response.code == UNAUTHORIZATION
                while (isRefreshed) {
                    response.close()
                    response = chain.proceed(
                        request.newBuilder()
                            .addHeader(
                                "Authorization",
                                "${Constants.BEARER}${userDataStoreDataSource.refreshToken}"
                            ).build()
                    )
                }
                response
            }
        }


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

}