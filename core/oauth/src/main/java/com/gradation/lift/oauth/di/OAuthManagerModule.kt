package com.gradation.lift.oauth.di

import android.content.Context
import com.gradation.lift.oauth.common.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object OAuthManagerModule {


    @ActivityScoped
    @Provides
    fun provideOAuthSignInManager(
        @ActivityContext context: Context,
    ): OAuthSignInManager= OAuthSignInManager(context)


}