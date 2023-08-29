package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.datasource.history.DefaultHistoryDataSource
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.HistoryService
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
class HistoryDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var historyService: HistoryService
    private lateinit var historyDataSource: HistoryDataSource
    private lateinit var networkResultHandler: NetworkResultHandler

    private val dispatcher: DispatcherProvider = testDispatchers()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        historyService = TestServiceModule.testHistoryService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        historyDataSource = DefaultHistoryDataSource(
            historyService,
            networkResultHandler = networkResultHandler
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getHistoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.History.historyResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(historyDataSource.getHistory().first()) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.History.historyModelList)
            ).isEqualTo(this)
        }
    }


    @Test
    fun getHistoryByHistoryIdDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.History.historyResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(historyDataSource.getHistoryByHistoryId(setOf(12, 13, 14)).first()) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.History.historyModelList)
            ).isEqualTo(this)
        }
    }

    @Test
    fun createHistoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(historyDataSource.createHistory(ModelDataGenerator.History.createHistoryModel).first()) {
            Truth.assertThat(
                NetworkResult.Success(Unit)
            ).isEqualTo(this)
        }
    }

    @Test
    fun deleteHistoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(historyDataSource.deleteHistory(1).first()) {
            Truth.assertThat(
                NetworkResult.Success(Unit)
            ).isEqualTo(this)
        }
    }


}