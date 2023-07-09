package com.gradation.lift.network.di

import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.handler.AuthAuthenticator
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthenticatorModule {

    @Singleton
    @Provides
    fun provideAuthAuthenticator(
        tokenDataStoreDataSource: TokenDataStoreDataSource,
        moshi: Moshi,
    ): AuthAuthenticator =
        AuthAuthenticator(
            tokenDataStoreDataSource = tokenDataStoreDataSource,
            moshi = moshi
        )

}