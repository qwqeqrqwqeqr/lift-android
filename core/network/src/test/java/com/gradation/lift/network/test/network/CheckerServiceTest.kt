package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Checker.CheckDuplicateEmail.CHECK_DUPLICATE_EMAIL_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Checker.CheckDuplicateName.CHECK_DUPLICATE_NAME_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Checker.CHECK_DUPLICATE_EMAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Checker.CHECK_DUPLICATE_NAME_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.CheckerService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


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
    fun checkDuplicateEmailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CHECK_DUPLICATE_EMAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = checkerService.checkDuplicateEmail(FAKE_EMAIL_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/checker/duplicate-email?email=${FAKE_EMAIL_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data).isEqualTo(CHECK_DUPLICATE_EMAIL_RESPONSE_DTO)
    }


    @Test
    fun checkDuplicateNameService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CHECK_DUPLICATE_NAME_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = checkerService.checkDuplicateName(FAKE_STRING_DATA)

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/checker/duplicate-name?name=${FAKE_STRING_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data).isEqualTo(CHECK_DUPLICATE_NAME_RESPONSE_DTO)
    }

}