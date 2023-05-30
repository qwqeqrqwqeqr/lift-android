package com.gradation.lift.network.common

import com.gradation.lift.network.BuildConfig


object Constants {
    const val DEFAULT_TIMEOUT = 10000
    const val NETWORK_RETRY_DELAY = 1000L
    const val BASE_URL = BuildConfig.LIFT_API_URL


    const val INTERNAL_ERROR =500

}

