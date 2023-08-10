package com.gradation.lift.network.utils

import retrofit2.HttpException
import java.net.SocketTimeoutException


fun Throwable.toMessage() =
    when (this) {
        is SocketTimeoutException -> "접속이 원활하지 않습니다."
        is HttpException -> "네트워크 통신에 실패하였습니다."
        else -> "알 수 없는 오류"
    }

