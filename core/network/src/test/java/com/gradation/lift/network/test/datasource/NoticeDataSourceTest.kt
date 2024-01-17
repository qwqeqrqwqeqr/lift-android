package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.Notification.noticeResponseJson
import com.gradation.lift.network.datasource.notice.DefaultNoticeDefaultDataSource
import com.gradation.lift.network.datasource.notice.NoticeDataSource
import com.gradation.lift.network.di.TestDispatcher
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.NoticeService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest

class NoticeDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var noticeService: NoticeService
    private lateinit var noticeDataSource: NoticeDataSource
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
        noticeDataSource = DefaultNoticeDefaultDataSource(
            noticeService, networkResultHandler
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
                .setBody(noticeResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(noticeDataSource.getNotice().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(ModelDataGenerator.Notification.noticeModel))
            ).isEqualTo(this)
        }
    }
}