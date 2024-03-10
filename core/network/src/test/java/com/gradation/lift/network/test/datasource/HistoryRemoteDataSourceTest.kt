package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.History.CREATE_HISTORY_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.History.HISTORY_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.History.UPDATE_HISTORY_INFO_MODEL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.History.CREATE_HISTORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.DELETE_HISTORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.GET_HISTORY_BY_HISTORY_ID_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.GET_HISTORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.UPDATE_HISTORY_INFO_RESPONSE_JSON
import com.gradation.lift.network.datasource.history.DefaultHistoryRemoteDataSource
import com.gradation.lift.network.datasource.history.HistoryRemoteDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.HistoryService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class HistoryRemoteDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var historyService: HistoryService
    private lateinit var historyRemoteDataSource: HistoryRemoteDataSource
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
        historyRemoteDataSource = DefaultHistoryRemoteDataSource(
            historyService,
            networkResultHandler = networkResultHandler, dispatcher
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
                .setBody(GET_HISTORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(historyRemoteDataSource.getHistory().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(HISTORY_MODEL))
            ).isEqualTo(this)
        }
    }


    @Test
    fun getHistoryByHistoryIdDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_HISTORY_BY_HISTORY_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(historyRemoteDataSource.getHistoryByHistoryId(setOf(FAKE_INT_DATA)).first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(HISTORY_MODEL))
            ).isEqualTo(this)
        }
    }

    @Test
    fun createHistoryDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_HISTORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(historyRemoteDataSource.createHistory(CREATE_HISTORY_MODEL).first()) {
            Truth.assertThat(
                NetworkResult.Success(Unit)
            ).isEqualTo(this)
        }
    }

    @Test
    fun deleteHistoryDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(DELETE_HISTORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(historyRemoteDataSource.deleteHistory(FAKE_INT_DATA).first()) {
            Truth.assertThat(
                NetworkResult.Success(Unit)
            ).isEqualTo(this)
        }
    }

    @Test
    fun updateHistoryInfoDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_HISTORY_INFO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(historyRemoteDataSource.updateHistoryInfo(UPDATE_HISTORY_INFO_MODEL).first()) {
            Truth.assertThat(
                NetworkResult.Success(FAKE_BOOLEAN_DATA)
            ).isEqualTo(this)
        }
    }


}