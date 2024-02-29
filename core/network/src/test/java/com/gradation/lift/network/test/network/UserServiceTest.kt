package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.Constants.CREATED
import com.gradation.lift.network.common.Constants.OK
import com.gradation.lift.network.data.TestDtoDataGenerator.User.CreateUserDetail.CREATE_USER_DETAIL_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.CreateUserDetail.CREATE_USER_DETAIL_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.DeleteUser.DELETE_USER_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.DeleteUser.DELETE_USER_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.ExistUserDetail.EXIST_USER_DETAIL_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.GetAuthenticationMethod.GET_USER_AUTHENTICATION_METHOD_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.GetUserDetail.GET_USER_DETAIL_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetail.UPDATE_USER_DETAIL_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetail.UPDATE_USER_DETAIL_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetailInfo.UPDATE_USER_DETAIL_INFO_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetailInfo.UPDATE_USER_DETAIL_INFO_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetailName.UPDATE_USER_DETAIL_NAME_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetailName.UPDATE_USER_DETAIL_NAME_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetailProfilePicture.UPDATE_USER_DETAIL_PROFILE_PICTURE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.User.UpdateUserDetailProfilePicture.UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.User.CREATE_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.DELETE_USER_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.EXIST_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.GET_USER_AUTHENTICATION_METHOD_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.GET_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_INFO_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_NAME_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.User.UPDATE_USER_DETAIL_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule.testUserService
import com.gradation.lift.network.service.UserService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


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
    fun getUserAuthenticationMethodService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_AUTHENTICATION_METHOD_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(OK)
        )

        val response = userService.getUserAuthenticationMethod()
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/authentication-method")
        assertThat(request.method).isEqualTo(Constants.GET)

        assertThat(response.code()).isEqualTo(OK)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(GET_USER_AUTHENTICATION_METHOD_RESPONSE_DTO)
    }

    @Test
    fun getUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(OK)
        )

        val response = userService.getUserDetail()
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail")
        assertThat(request.method).isEqualTo(Constants.GET)

        assertThat(response.code()).isEqualTo(OK)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(GET_USER_DETAIL_RESPONSE_DTO)
    }


    @Test
    fun updateUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.updateUserDetail(UPDATE_USER_DETAIL_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail")
        assertThat(request.method).isEqualTo(Constants.PUT)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(UPDATE_USER_DETAIL_RESPONSE_DTO)
    }


    @Test
    fun createUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.createUserDetail(CREATE_USER_DETAIL_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail")
        assertThat(request.method).isEqualTo(Constants.POST)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(CREATE_USER_DETAIL_RESPONSE_DTO)
    }

    @Test
    fun updateUserDetailInfoService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_DETAIL_INFO_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.updateUserDetailInfo(UPDATE_USER_DETAIL_INFO_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail/info")
        assertThat(request.method).isEqualTo(Constants.PATCH)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(UPDATE_USER_DETAIL_INFO_RESPONSE_DTO)
    }

    @Test
    fun updateUserDetailProfilePictureService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.updateUserDetailProfilePicture(
            UPDATE_USER_DETAIL_PROFILE_PICTURE_REQUEST_DTO
        )
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail/profile-picture")
        assertThat(request.method).isEqualTo(Constants.PATCH)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(UPDATE_USER_DETAIL_PROFILE_PICTURE_RESPONSE_DTO)
    }

    @Test
    fun updateUserDetailNameService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_DETAIL_NAME_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.updateUserDetailName(UPDATE_USER_DETAIL_NAME_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/user-detail/name")
        assertThat(request.method).isEqualTo(Constants.PATCH)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(UPDATE_USER_DETAIL_NAME_RESPONSE_DTO)
    }


    @Test
    fun existUserDetailService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(EXIST_USER_DETAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(OK)
        )


        val response = userService.existUserDetail()
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user/exist-user-detail")
        assertThat(request.method).isEqualTo(Constants.GET)

        assertThat(response.code()).isEqualTo(OK)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(EXIST_USER_DETAIL_RESPONSE_DTO)
    }


    @Test
    fun deleteUserService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(DELETE_USER_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(CREATED)
        )

        val response = userService.deleteUser(DELETE_USER_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/user")
        assertThat(request.method).isEqualTo(Constants.POST)

        assertThat(response.code()).isEqualTo(CREATED)
        assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        assertThat(response.body()!!.data).isEqualTo(DELETE_USER_RESPONSE_DTO)
    }


}
