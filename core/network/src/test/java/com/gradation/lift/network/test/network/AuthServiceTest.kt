package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInDefaultRequestDto
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInDefaultResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInKakaoRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInKakaoResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInNaverRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInNaverResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signUpDefaultRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signUpDefaultResponseDto
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.signInDefaultResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.signInKakaoResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.signInNaverResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.service.AuthService
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
class AuthServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var authService: AuthService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        authService = TestServiceModule.testAuthService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun signInDefaultService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(signInDefaultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signInDefault(signInDefaultRequestDto = signInDefaultRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-in/default")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(signInDefaultResponseDto)
    }

    @Test
    fun signInKakaoService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(signInKakaoResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signInKakao(signInKakaoRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-in/kakao")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(signInKakaoResponseDto)
    }
    @Test
    fun signInNaverService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(signInNaverResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signInNaver(signInNaverRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-in/naver")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(signInNaverResponseDto)
    }


    @Test
    fun signUpDefaultService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signUpDefault(signUpDefaultRequestDto = signUpDefaultRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-up/default")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(signUpDefaultResponseDto)
    }
}