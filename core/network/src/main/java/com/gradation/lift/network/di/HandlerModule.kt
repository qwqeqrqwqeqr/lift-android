package com.gradation.lift.network.di

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.handler.DefaultNetworkResultHandler
import com.gradation.lift.network.handler.NetworkResultHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HandlerModule {


    @Provides
    @Singleton
    fun provideNetworkResultHandler(dispatcherProvider: DispatcherProvider): NetworkResultHandler =
        DefaultNetworkResultHandler(dispatcherProvider)
}







