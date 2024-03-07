package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_MESSAGE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.EMAIL_AUTHENTICATION_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.EMAIL_AUTHENTICATION_VALIDATION_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.GOOGLE_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.KAKAO_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.NAVER_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.TOKEN_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.UPDATE_PASSWORD_INFO_MODEL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator
import com.gradation.lift.network.datasource.auth.AuthRemoteDataSource
import com.gradation.lift.network.datasource.auth.DefaultAuthRemoteDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.AuthService
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
class AuthRemoteDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var authService: AuthService
    private lateinit var authRemoteDataSource: AuthRemoteDataSource
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
        authService = TestServiceModule.testAuthService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        authRemoteDataSource = DefaultAuthRemoteDataSource(
            authService = authService,
            networkResultHandler = networkResultHandler,
            dispatcherProvider = dispatcher
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun signInDefaultDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_IN_DEFAULT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(authRemoteDataSource.signInKakao(signInInfo = KAKAO_SIGN_IN_INFO_MODEL).first()) {
            Truth.assertThat(NetworkResult.Success(TOKEN_MODEL)).isEqualTo(this)
        }
    }


    @Test
    fun signUpDefaultDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_UP_DEFAULT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.signUpDefault(ModelDataGenerator.Auth.DEFAULT_SIGN_UP_INFO_MODEL)
                .first()
        ) {
            Truth.assertThat(NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA))
                .isEqualTo(this)
        }
    }

    @Test
    fun signInKakaoDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_IN_KAKAO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(authRemoteDataSource.signInKakao(signInInfo = KAKAO_SIGN_IN_INFO_MODEL).first()) {
            Truth.assertThat(NetworkResult.Success(TOKEN_MODEL)).isEqualTo(this)
        }
    }

    @Test
    fun signUpKakaoDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_UP_KAKAO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.signUpKakao(ModelDataGenerator.Auth.KAKAO_SIGN_UP_INFO_MODEL)
                .first()
        ) {
            Truth.assertThat(NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA))
                .isEqualTo(this)
        }
    }


    @Test
    fun signInNaverDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_IN_NAVER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(authRemoteDataSource.signInNaver(signInInfo = NAVER_SIGN_IN_INFO_MODEL).first()) {
            Truth.assertThat(NetworkResult.Success(TOKEN_MODEL)).isEqualTo(this)
        }
    }

    @Test
    fun signUpNaverDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_UP_NAVER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.signUpNaver(ModelDataGenerator.Auth.NAVER_SIGN_UP_INFO_MODEL)
                .first()
        ) {
            Truth.assertThat(NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA))
                .isEqualTo(this)
        }
    }


    @Test
    fun signInGoogleDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_IN_GOOGLE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(authRemoteDataSource.signInGoogle(signInInfo = GOOGLE_SIGN_IN_INFO_MODEL).first()) {
            Truth.assertThat(NetworkResult.Success(TOKEN_MODEL)).isEqualTo(this)
        }
    }

    @Test
    fun signUpGoogleDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.SIGN_UP_GOOGLE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.signUpGoogle(ModelDataGenerator.Auth.GOOGLE_SIGN_UP_INFO_MODEL)
                .first()
        ) {
            Truth.assertThat(NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA))
                .isEqualTo(this)
        }
    }


    @Test
    fun checkUserExistDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.CHECK_EXIST_USER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.checkUserExist(userId = FAKE_STRING_DATA, email = FAKE_EMAIL_DATA)
                .first()
        ) {
            Truth.assertThat(NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA))
                .isEqualTo(this)
        }
    }


    @Test
    fun updateUserPasswordDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.UPDATE_PASSWORD_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.updateUserPassword(UPDATE_PASSWORD_INFO_MODEL).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(
                    DefaultDataGenerator.FAKE_BOOLEAN_DATA,
                    message = FAKE_MESSAGE_DATA
                )
            )
                .isEqualTo(this)
        }
    }


    @Test
    fun createEmailAuthenticationCodeDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.createEmailAuthenticationCode(EMAIL_AUTHENTICATION_INFO_MODEL)
                .first()
        ) {
            Truth.assertThat(NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA))
                .isEqualTo(this)
        }
    }

    @Test
    fun validateEmailAuthenticationDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            authRemoteDataSource.validateEmailAuthentication(
                EMAIL_AUTHENTICATION_VALIDATION_INFO_MODEL
            )
                .first()
        ) {
            Truth.assertThat(NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA))
                .isEqualTo(this)
        }
    }
}