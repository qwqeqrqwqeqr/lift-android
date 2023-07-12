package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestCheckerDataGenerator
import com.gradation.lift.network.data.TestCheckerDataGenerator.checkDuplicateEmailResponseDto
import com.gradation.lift.network.data.TestCheckerDataGenerator.checkDuplicateNameResponseDto
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.CheckerService
import com.gradation.lift.network.service.UserService
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA
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
class CheckerServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var checkerService: CheckerService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        checkerService = TestServiceModule.testCheckerService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun testCheckDuplicateEmailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestCheckerDataGenerator.resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = checkerService.checkDuplicateEmail(FAKE_STRING_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/checker/duplicate-email/?email=${FAKE_STRING_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data).isEqualTo(checkDuplicateEmailResponseDto)
    }


    @Test
    fun testCheckDuplicateNameService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestCheckerDataGenerator.resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = checkerService.checkDuplicateName(FAKE_STRING_DATA)

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/checker/duplicate-name/?name=${FAKE_STRING_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data).isEqualTo(checkDuplicateNameResponseDto)
    }

}