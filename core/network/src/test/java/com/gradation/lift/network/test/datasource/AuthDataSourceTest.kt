package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.datasource.auth.DefaultAuthDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
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
class AuthDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var authService: AuthService
    private lateinit var authDataSource: AuthDataSource
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
        authDataSource = DefaultAuthDataSource(
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
                .setBody(TestJsonDataGenerator.Auth.signInDefaultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(authDataSource.signInKakao(signInInfo = ModelDataGenerator.Auth.kakaoSignInInfoModel).first()) {
           Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.Auth.tokenModel)
            ).isEqualTo(
                this
            )
        }
    }


    @Test
    fun signInKakaoDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.signInKakaoResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(authDataSource.signInNaver(signInInfo = ModelDataGenerator.Auth.naverSignInInfoModel).first()) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.Auth.tokenModel)
            ).isEqualTo(
                this
            )
        }
    }

    @Test
    fun signInNaverDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Auth.signInNaverResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(authDataSource.signInNaver(signInInfo = ModelDataGenerator.Auth.naverSignInInfoModel).first()) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.Auth.tokenModel)
            ).isEqualTo(
                this
            )
        }
    }


    @Test
    fun signUpDefaultDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(authDataSource.signUpDefault(ModelDataGenerator.Auth.defaultSignUpInfoModel).first()) {
            Truth.assertThat(
                NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA)
            ).isEqualTo(
                this
            )
        }
    }


}