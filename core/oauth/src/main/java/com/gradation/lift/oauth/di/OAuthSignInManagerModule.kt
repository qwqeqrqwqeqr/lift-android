package com.gradation.lift.oauth.di

import android.content.Context
import com.gradation.lift.oauth.common.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

/**
 * [OAuthSignInManagerModule]
 * Activity context 접근하기 위해
 * 개별적으로 모듈 구성
 * Activity Scope 사용
 */
@Module
@InstallIn(ActivityComponent::class)
object OAuthSignInManagerModule {


    @ActivityScoped
    @Provides
    fun provideOAuthSignInManager(
        @ActivityContext context: Context,
    ): OAuthSignInManager= DefaultOauthSignInManager(context)






}