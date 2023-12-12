package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.Constants.CREATED
import com.gradation.lift.network.common.Constants.OK
import com.gradation.lift.network.data.TestDtoDataGenerator.User.createUserDetailRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.createUserDetailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.existUserDetailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.getUserDetailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.updateUserDetailRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.updateUserDetailResponseDto
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.User.userDetailResponseJson
import com.gradation.lift.network.di.TestServiceModule.testUserService
import com.gradation.lift.network.di.TestRetrofit
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
class UserServiceTest {


    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userService: UserService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        userService = testUserService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun existUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(OK)
        )


        val response = userService.existUserDetail()
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/exist-user-detail")
        assertThat(request.method).isEqualTo(Constants.GET)

        assertThat(response.code()).isEqualTo(OK)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(existUserDetailResponseDto)
    }
    @Test
    fun getUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(userDetailResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(OK)
        )

        val response = userService.getUserDetail()
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail")
        assertThat(request.method).isEqualTo(Constants.GET)

        assertThat(response.code()).isEqualTo(OK)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(getUserDetailResponseDto)
    }

    @Test
    fun createUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.createUserDetail(createUserDetailRequestDto)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail")
        assertThat(request.method).isEqualTo(Constants.POST)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(createUserDetailResponseDto)
    }

    @Test
    fun updateUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.updateUserDetail(updateUserDetailRequestDto)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail")
        assertThat(request.method).isEqualTo(Constants.PUT)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(updateUserDetailResponseDto)
    }


}
