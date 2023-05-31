package com.gradation.lift.network.di

import com.gradation.lift.network.common.DefaultNetworkResultHandler
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.fake.FakeDispatcherProvider
import dagger.Provides
import javax.inject.Singleton

object FakeNetworkModule {
    @Provides
    @Singleton
    fun provideNetworkResultHandler(): NetworkResultHandler =
        DefaultNetworkResultHandler(FakeDispatcherProvider())
}