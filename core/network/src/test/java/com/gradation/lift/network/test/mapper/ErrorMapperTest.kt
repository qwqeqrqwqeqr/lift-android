package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.network.common.error.toMessage
import org.junit.Test
import java.net.SocketTimeoutException


class ErrorMapperTest {

    @Test
    fun errorMapper() {
        Truth.assertThat(
            Throwable(SocketTimeoutException()).toMessage()
        ).isEqualTo(
            "접속이 원활하지 않습니다."
        )

        Truth.assertThat(
            Throwable().toMessage()
        ).isEqualTo(
            "알 수 없는 오류"
        )


    }
}