package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.LOGIN_METHOD_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.User.DELETE_USER_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.User.USER_DETAIL_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.User.USER_DETAIL_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.User.USER_DETAIL_NAME_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.User.USER_DETAIL_PROFILE_PICTURE_MODEL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.User.CREATE_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.DELETE_USER_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.EXIST_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.GET_USER_AUTHENTICATION_METHOD_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.GET_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_INFO_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_NAME_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.datasource.user.DefaultUserRemoteDataSource
import com.gradation.lift.network.datasource.user.UserRemoteDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
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
class UserRemoteDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userService: UserService
    private lateinit var userRemoteDataSource: UserRemoteDataSource
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
        userRemoteDataSource = DefaultUserRemoteDataSource(
            userService = userService,
            networkResultHandler = networkResultHandler, dispatcher
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun deleteUserDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(DELETE_USER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(userRemoteDataSource.deleteUser(DELETE_USER_INFO_MODEL).first()) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }


    @Test
    fun getUserDetailDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(userRemoteDataSource.getUserDetail().first()) {
            Truth.assertThat(
                NetworkResult.Success(USER_DETAIL_MODEL),
            ).isEqualTo(this)
        }
    }


    @Test
    fun getUserAuthenticationMethodDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_AUTHENTICATION_METHOD_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(userRemoteDataSource.getUserAuthenticationMethod().first()) {
            Truth.assertThat(
                NetworkResult.Success(LOGIN_METHOD_MODEL),
            ).isEqualTo(this)
        }
    }


    @Test
    fun createUserDetailDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            userRemoteDataSource.createUserDetail(USER_DETAIL_MODEL).first()
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
                .setBody(UPDATE_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            userRemoteDataSource.updateUserDetail(USER_DETAIL_MODEL).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }


    @Test
    fun updateUserDetailProfilePictureDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            userRemoteDataSource.updateUserDetailProfilePicture(USER_DETAIL_PROFILE_PICTURE_MODEL)
                .first()
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
                .setBody(EXIST_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(userRemoteDataSource.existUserDetail().first()) {
            Truth.assertThat(
                NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA),
            ).isEqualTo(this)
        }
    }

    @Test
    fun updateUserDetailNameDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_DETAIL_NAME_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            userRemoteDataSource.updateUserDetailName(USER_DETAIL_NAME_MODEL).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(
                    DefaultDataGenerator.FAKE_BOOLEAN_DATA,
                    message = DefaultDataGenerator.FAKE_MESSAGE_DATA
                ),
            ).isEqualTo(this)
        }
    }


    @Test
    fun updateUserDetailInfoDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_DETAIL_INFO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            userRemoteDataSource.updateUserDetailInfo(USER_DETAIL_INFO_MODEL).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA),
            ).isEqualTo(this)
        }
    }


}