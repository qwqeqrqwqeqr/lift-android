package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestRefreshDtoDataGenerator
import com.gradation.lift.network.data.TestUserDtoDataGenerator
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.AuthService
import com.gradation.lift.network.service.RefreshService
import com.gradation.lift.network.service.UserService
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
    fun testRefreshService() = runTest {

        val refreshToken = TestRefreshDtoDataGenerator.TEST_REFRESH_TOKEN
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestRefreshDtoDataGenerator.refreshResponseJson)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization",refreshToken)
                .setResponseCode(Constants.CREATED)

        )
        val response = refreshService.refresh(refreshToken)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/refresh/")
        Truth.assertThat(request.getHeader("Authorization")).isEqualTo(refreshToken)
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(TestRefreshDtoDataGenerator.refreshResponseDto)
    }

}