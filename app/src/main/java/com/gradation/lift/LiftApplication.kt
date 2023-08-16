package com.gradation.lift

import android.app.Application
import com.gradation.lift.BuildConfig.KAKAO_APP_KEY
import com.gradation.lift.oauth.common.kakaoInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LiftApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        kakaoInitializer(this,KAKAO_APP_KEY)

    }
}