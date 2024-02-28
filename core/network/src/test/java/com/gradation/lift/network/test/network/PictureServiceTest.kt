package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.GetRoutineSetPicture.GET_ROUTINE_SET_PICTURE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.GetUserProfilePicture.GET_USER_PROFILE_PICTURE_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Picture.GET_ROUTINE_SET_PICTURE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Picture.GET_USER_PROFILE_PICTURE_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.PictureService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class PictureServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var pictureService: PictureService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        pictureService = TestServiceModule.testPictureService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getRoutineSetPictureService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_PICTURE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = pictureService.getRoutineSetPicture()

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/picture/routine-set")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_ROUTINE_SET_PICTURE_RESPONSE_DTO)
    }

    @Test
    fun getUserProfilePictureService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_PROFILE_PICTURE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = pictureService.getUserProfilePicture()

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/picture/user-profile")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_USER_PROFILE_PICTURE_RESPONSE_DTO)
    }


}