package com.gradation.lift.oauth.di

import android.content.Context
import com.gradation.lift.oauth.common.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * [provideOAuthConnectManager]
 * Activity context 접근하기 위해
 * 개별적으로 모듈 구성
 * Activity Scope 사용
 * Repository 레이어에서 사용 불가능
 * @since 2023-08-16 11:00:55
 */
@Module
@InstallIn(SingletonComponent::class)
object OAuthSignInManagerModule {


    @Singleton
    @Provides
    fun provideOAuthConnectManager(
        @ApplicationContext context: Context,
    ): OAuthConnectionManager = DefaultOAuthConnectionManager(context)


}