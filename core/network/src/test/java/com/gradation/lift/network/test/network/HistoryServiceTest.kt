package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.History.CreateHistory.CREATE_HISTORY_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.History.CreateHistory.CREATE_HISTORY_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.History.DeleteHistory.DELETE_HISTORY_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.History.GetHistory.GET_HISTORY_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.History.GetHistoryByHistoryID.GET_HISTORY_BY_HISTORY_ID_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.History.UpdateHistoryInfo.UPDATE_HISTORY_INFO_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.History.UpdateHistoryInfo.UPDATE_HISTORY_INFO_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.History.CREATE_HISTORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.DELETE_HISTORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.GET_HISTORY_BY_HISTORY_ID_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.GET_HISTORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.History.UPDATE_HISTORY_INFO_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.HistoryService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


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
    fun getHistoryService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_HISTORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.getHistory()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_HISTORY_RESPONSE_DTO)
    }


    @Test
    fun createHistoryService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_HISTORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.createHistory(CREATE_HISTORY_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(CREATE_HISTORY_RESPONSE_DTO)
    }

    @Test
    fun deleteHistoryService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(DELETE_HISTORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.deleteHistory(FAKE_INT_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history?history_id=$FAKE_INT_DATA")
        Truth.assertThat(request.method).isEqualTo(Constants.DELETE)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(DELETE_HISTORY_RESPONSE_DTO)
    }


    @Test
    fun getHistoryByHistoryIdService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_HISTORY_BY_HISTORY_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = historyService.getHistoryByHistoryId(FAKE_INT_DATA.toString())
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history-by-history-id?history_id_list=$FAKE_INT_DATA")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_HISTORY_BY_HISTORY_ID_RESPONSE_DTO)
    }


    @Test
    fun updateHistoryInfoService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_HISTORY_INFO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response = historyService.updateHistoryInfo(UPDATE_HISTORY_INFO_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/history/history-info")
        Truth.assertThat(request.method).isEqualTo(Constants.PATCH)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(UPDATE_HISTORY_INFO_RESPONSE_DTO)
    }

}