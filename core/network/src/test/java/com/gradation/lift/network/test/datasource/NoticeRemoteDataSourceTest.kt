package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Notice.NOTICE_MODEL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.Notice.GET_NOTICE_BY_ID_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Notice.GET_NOTICE_RESPONSE_JSON
import com.gradation.lift.network.datasource.notice.DefaultNoticeDefaultRemoteDataSource
import com.gradation.lift.network.datasource.notice.NoticeRemoteDataSource
import com.gradation.lift.network.di.TestDispatcher
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.NoticeService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest

class NoticeRemoteDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var noticeService: NoticeService
    private lateinit var noticeRemoteDataSource: NoticeRemoteDataSource
    private lateinit var networkResultHandler: NetworkResultHandler

    private val dispatcher: DispatcherProvider = TestDispatcher.testDispatchers()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        noticeService = TestServiceModule.testNotificationService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        noticeRemoteDataSource = DefaultNoticeDefaultRemoteDataSource(
            noticeService, networkResultHandler, dispatcher
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getNotice() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_NOTICE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(noticeRemoteDataSource.getNotice().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(NOTICE_MODEL))
            ).isEqualTo(this)
        }
    }

    @Test
    fun getNoticeById() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_NOTICE_BY_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(noticeRemoteDataSource.getNoticeById(FAKE_INT_DATA).first()) {
            Truth.assertThat(
                NetworkResult.Success(NOTICE_MODEL)
            ).isEqualTo(this)
        }
    }
}