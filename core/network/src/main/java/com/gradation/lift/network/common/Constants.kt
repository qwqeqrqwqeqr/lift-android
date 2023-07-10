package com.gradation.lift.network.common

import com.gradation.lift.network.BuildConfig


object Constants {
    const val DEFAULT_TIMEOUT = 30000
    const val NETWORK_RETRY_DELAY = 500L
    const val BASE_URL = BuildConfig.LIFT_API_URL

    const val BEARER = "Bearer "

    const val OK = 200
    const val BAD_REQUEST = 400
    const val UNAUTHORIZATION = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val METHOD_NOT_ALLOWED = 405
    const val INTERNAL_SERVER_ERROR = 500

}

