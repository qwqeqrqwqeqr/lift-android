package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.CheckExistUser.CHECK_EXIST_USER_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.CreateEmailAuthenticationCode.CREATE_EMAIL_AUTHENTICATION_CODE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.CreateEmailAuthenticationCode.CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInDefault.SIGN_IN_DEFAULT_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInDefault.SIGN_IN_DEFAULT_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInGoogle.SIGN_IN_GOOGLE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInGoogle.SIGN_IN_GOOGLE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInKakao.SIGN_IN_KAKAO_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInKakao.SIGN_IN_KAKAO_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInNaver.SIGN_IN_NAVER_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInNaver.SIGN_IN_NAVER_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpDefault.SIGN_UP_DEFAULT_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpDefault.SIGN_UP_DEFAULT_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpGoogle.SIGN_UP_GOOGLE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpGoogle.SIGN_UP_GOOGLE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpKakao.SIGN_UP_KAKAO_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpKakao.SIGN_UP_KAKAO_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpNaver.SIGN_UP_NAVER_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpNaver.SIGN_UP_NAVER_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.UpdateUserPassword.UPDATE_PASSWORD_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.UpdateUserPassword.UPDATE_PASSWORD_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.ValidateEmailAuthentication.VALIDATE_EMAIL_AUTHENTICATION_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.ValidateEmailAuthentication.VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.CHECK_EXIST_USER_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_IN_DEFAULT_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_IN_GOOGLE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_IN_KAKAO_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_IN_NAVER_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_UP_DEFAULT_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_UP_GOOGLE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_UP_KAKAO_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.SIGN_UP_NAVER_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.UPDATE_PASSWORD_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Auth.VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.AuthService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


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
    fun signUpDefaultService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_UP_DEFAULT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response =
            authService.signUpDefault(signUpDefaultRequestDto = SIGN_UP_DEFAULT_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-up/default")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_UP_DEFAULT_RESPONSE_DTO)
    }


    @Test
    fun signInDefaultService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_IN_DEFAULT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response =
            authService.signInDefault(signInDefaultRequestDto = SIGN_IN_DEFAULT_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-in/default")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_IN_DEFAULT_RESPONSE_DTO)
    }


    @Test
    fun signUpGoogleService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_UP_GOOGLE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signUpGoogle(SIGN_UP_GOOGLE_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-up/google")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_UP_GOOGLE_RESPONSE_DTO)
    }

    @Test
    fun signInGoogleService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_IN_GOOGLE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signInGoogle(SIGN_IN_GOOGLE_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-in/google")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_IN_GOOGLE_RESPONSE_DTO)
    }

    @Test
    fun signUpKakaoService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_UP_KAKAO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signUpKakao(SIGN_UP_KAKAO_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-up/kakao")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_UP_KAKAO_RESPONSE_DTO)
    }

    @Test
    fun signInKakaoService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_IN_KAKAO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signInKakao(SIGN_IN_KAKAO_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-in/kakao")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_IN_KAKAO_RESPONSE_DTO)
    }


    @Test
    fun signUpNaverService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_UP_NAVER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signUpNaver(SIGN_UP_NAVER_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-up/naver")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_UP_NAVER_RESPONSE_DTO)
    }

    @Test
    fun signInNaverService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(SIGN_IN_NAVER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response = authService.signInNaver(SIGN_IN_NAVER_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/auth/sign-in/naver")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(SIGN_IN_NAVER_RESPONSE_DTO)
    }


    @Test
    fun checkExistUserService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CHECK_EXIST_USER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)

        )
        val response =
            authService.checkExistUser(userId = FAKE_STRING_DATA, email = FAKE_EMAIL_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/auth/exist/user?user_id=${FAKE_STRING_DATA}&email=${FAKE_EMAIL_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(CHECK_EXIST_USER_RESPONSE_DTO)
    }


    @Test
    fun createEmailAuthenticationCodeService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response =
            authService.createEmailAuthenticationCode(CREATE_EMAIL_AUTHENTICATION_CODE_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/auth/authentication-email/code")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_DTO)
    }

    @Test
    fun validateEmailAuthenticationService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response =
            authService.validateEmailAuthentication(VALIDATE_EMAIL_AUTHENTICATION_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/auth/authentication-email/validation")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_DTO)
    }


    @Test
    fun updateUserPasswordService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_PASSWORD_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)

        )
        val response =
            authService.updateUserPassword(UPDATE_PASSWORD_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/auth/password")
        Truth.assertThat(request.method).isEqualTo(Constants.PATCH)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(UPDATE_PASSWORD_RESPONSE_DTO)
    }


}