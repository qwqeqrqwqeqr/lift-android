package com.gradation.lift.data.di

import com.gradation.lift.data.repository.DefaultOAuthConnectionRepository
import com.gradation.lift.domain.repository.OAuthConnectionRepository
import com.gradation.lift.oauth.common.OAuthConnectionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object OAuthConnectionRepositoryModule {


    @Provides
    fun provideOAuthConnectionRepository(
        oAuthConnectionManager: OAuthConnectionManager,
    ): OAuthConnectionRepository = DefaultOAuthConnectionRepository(oAuthConnectionManager)


}