package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.datasource.user.DefaultUserDataSource
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.UserService
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
class UserDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userService: UserService
    private lateinit var userDataSource: UserDataSource
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
        userService = TestServiceModule.testUserService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        userDataSource = DefaultUserDataSource(
            userService = userService,
            networkResultHandler = networkResultHandler,dispatcher
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getUserDetailDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.User.userDetailResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(userDataSource.getUserDetail().first()) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.User.userDetailModel),
            ).isEqualTo(this)
        }
    }


    @Test
    fun createUserDetailDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            userDataSource.createUserDetail(ModelDataGenerator.User.createUserDetailModel).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }

    @Test
    fun updateUserDetailDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            userDataSource.updateUserDetail(ModelDataGenerator.User.userDetailModel).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }

    @Test
    fun existUserDetailDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(userDataSource.existUserDetail().first()) {
            Truth.assertThat(
                NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA),
            ).isEqualTo(this)
        }
    }


}