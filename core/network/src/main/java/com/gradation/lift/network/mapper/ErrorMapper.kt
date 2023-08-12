package com.gradation.lift.network.mapper

import java.net.SocketTimeoutException


fun Throwable.toMessage() =
    when (cause) {
        is SocketTimeoutException -> "접속이 원활하지 않습니다."
        else -> "알 수 없는 오류"
    }

