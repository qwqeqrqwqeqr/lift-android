package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Refresh.Refresh.REFRESH_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Refresh.REFRESH_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.RefreshService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class RefreshServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var refreshService: RefreshService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        refreshService = TestServiceModule.testRefreshService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun refreshService() = runTest {

        val refreshToken = FAKE_REFRESH_TOKEN
        mockWebServer.enqueue(
            MockResponse()
                .setBody(REFRESH_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", refreshToken)
                .setResponseCode(Constants.CREATED)

        )
        val response = refreshService.refresh(refreshToken)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/auth/refresh")
        assertThat(request.method).isEqualTo(Constants.POST)
        assertThat(request.getHeader("Authorization")).isEqualTo(refreshToken)

        assertThat(response.code()).isEqualTo(Constants.CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data)
            .isEqualTo(REFRESH_RESPONSE_DTO)
    }

}