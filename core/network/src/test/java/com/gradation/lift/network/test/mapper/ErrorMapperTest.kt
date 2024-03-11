package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.network.common.error.ErrorMessage.CAN_NOT_ACCESS_NETWORK_ERROR_MESSAGE
import com.gradation.lift.network.common.error.ErrorMessage.FAILED_CONNECTION_ERROR_MESSAGE
import com.gradation.lift.network.common.error.ErrorMessage.REQUEST_FAILED_ERROR_MESSAGE
import com.gradation.lift.network.common.error.ErrorMessage.TIME_OUT_ERROR_MESSAGE
import com.gradation.lift.network.common.error.toMessage
import org.junit.Test
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException


class ErrorMapperTest {

    @Test
    fun errorMapper() {
        Truth.assertThat(ConnectException().toMessage())
            .isEqualTo(CAN_NOT_ACCESS_NETWORK_ERROR_MESSAGE)
        Truth.assertThat(SocketTimeoutException().toMessage())
            .isEqualTo(TIME_OUT_ERROR_MESSAGE)
        Truth.assertThat(IOException().toMessage())
            .isEqualTo(REQUEST_FAILED_ERROR_MESSAGE)

        Truth.assertThat(Throwable().toMessage())
            .isEqualTo(FAILED_CONNECTION_ERROR_MESSAGE)


    }
}