package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Notice.GetNotice.GET_NOTICE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Notice.GetNoticeById.GET_NOTICE_BY_ID_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Notice.GET_NOTICE_BY_ID_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Notice.GET_NOTICE_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.NoticeService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class NoticeServiceTest {


    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var noticeService: NoticeService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        noticeService = TestServiceModule.testNotificationService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getNoticeService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_NOTICE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(noticeService.getNotice()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_NOTICE_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/notice/notice")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }

    @Test
    fun getNoticeByIdService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_NOTICE_BY_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(noticeService.getNoticeById(FAKE_INT_DATA)) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_NOTICE_BY_ID_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/notice/notice-by-id?notice_id=$FAKE_INT_DATA")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }
}