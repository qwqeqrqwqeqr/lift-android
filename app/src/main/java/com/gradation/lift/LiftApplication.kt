package com.gradation.lift

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LiftApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(context = this, BuildConfig.KAKAO_APP_KEY)
        NaverIdLoginSDK.initialize(
            context = this,
            clientId = BuildConfig.NAVER_OAUTH_CLIENT_ID,
            clientSecret = BuildConfig.NAVER_OAUTH_CLIENT_SECRET,
            clientName = BuildConfig.NAVER_OAUTH_CLIENT_NAME
        )
    }
}