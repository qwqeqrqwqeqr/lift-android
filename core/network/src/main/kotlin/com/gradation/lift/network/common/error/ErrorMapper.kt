package com.gradation.lift.network.common.error

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException


fun Throwable.toMessage():String =
    when (cause) {
        is ConnectException -> ErrorMessage.CAN_NOT_ACCESS_NETWORK_ERROR_MESSAGE
        is SocketTimeoutException -> ErrorMessage.TIME_OUT_ERROR_MESSAGE
        is IOException -> ErrorMessage.REQUEST_FAILED_ERROR_MESSAGE
        else -> ErrorMessage.FAILED_CONNECTION_ERROR_MESSAGE
    }

