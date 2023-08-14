package com.gradation.lift

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class LiftApplication: Application(){
    override fun onCreate() {
        super.onCreate()
    }
}