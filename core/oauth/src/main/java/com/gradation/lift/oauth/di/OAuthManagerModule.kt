package com.gradation.lift.oauth.di

import com.gradation.lift.oauth.common.*
import com.gradation.lift.oauth.kakao.DefaultKakaoOauthManager
import com.gradation.lift.oauth.naver.DefaultNaverOauthManager
import com.gradation.lift.oauth.kakao.KakaoOauthManager
import com.gradation.lift.oauth.naver.NaverOauthManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * [OAuthManagerModule]
 * Sign In 을 제외한
 * 나머지 기능 정의한 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
object OAuthManagerModule {


    @Provides
    fun provideKakaoOauthManager(
    ): KakaoOauthManager = DefaultKakaoOauthManager()


    @Provides
    fun provideNaverOauthManager(
    ): NaverOauthManager = DefaultNaverOauthManager()


}