package com.gradation.lift.network.di

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.network.handler.RefreshHandler
import com.gradation.lift.network.service.RefreshService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    @Singleton
    fun provideAuthHeaderInterceptor(
        userDataStoreDataSource: =,
        refreshHandler: RefreshHandler,
    ): Interceptor = AuthInterceptor(userDataStoreDataSource, refreshHandler)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
}


class AuthInterceptor @Inject constructor(
    private val userDataStoreDataSource: UserDataStoreDataSource,
    private val refreshService: RefreshService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val request = chain.request()
            chain.proceed(


                request.newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer ${ runBlocking {
                            async {
                                userDataStoreDataSource.accessToken.first()
                            }
                        }.await()}"
                    )
                    .url(request.url.toString().replace("%3D", "="))
                    .build()

            )
        }
    }
}


//        var isSuccess = response.isSuccessful
//                var tryCount = 0
//                val isRefreshed = response.code == UNAUTHORIZATION
//                while (isRefreshed) {
//                    response.close()
//                    runBlocking {
//                        refreshHandler.refresh()
//                    }
//                    response = chain.proceed(
//                        request.newBuilder()
//                            .addHeader(
//                                "Authorization",
//                                "${Constants.BEARER}${userDataStoreDataSource.refreshToken}"
//                            )
//                            .url(request.url.toString().replace("%3D", "="))
//                            .build()
//                    )
//                }
//                response
