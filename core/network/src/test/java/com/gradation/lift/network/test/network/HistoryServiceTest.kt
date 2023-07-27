package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator
import com.gradation.lift.network.data.TestDtoDataGenerator.History.createHistoryRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.createHistoryResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.deleteHistoryResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.getHistoryByHistoryIdResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.getHistoryResponseDto
import com.gradation.lift.network.data.TestJsonDataGenerator
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.History.historyResponseJson
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.CheckerService
import com.gradation.lift.network.service.HistoryService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
class HistoryServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var historyService: HistoryService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        historyService = TestServiceModule.testHistoryService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun testGetHistoryService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(historyResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.getHistory()

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history/")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getHistoryResponseDto)
    }


    @Test
    fun testGetHistoryByHistoryIdService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(historyResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.getHistoryByHistoryId("12,13,14")
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history-by-history-id/?history_id_list=12%2C13%2C14")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getHistoryByHistoryIdResponseDto)
    }

    @Test
    fun testCreateHistoryService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.createHistory(createHistoryRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history/")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(createHistoryResponseDto)
    }

    @Test
    fun testDeleteHistoryService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.deleteHistory(1)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history/?history_id=1")
        Truth.assertThat(request.method).isEqualTo(Constants.DELETE)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(deleteHistoryResponseDto)
    }

}