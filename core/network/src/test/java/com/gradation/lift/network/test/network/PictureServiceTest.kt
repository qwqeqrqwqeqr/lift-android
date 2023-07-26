package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.getRoutineSetPictureResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.getUserProfilePictureResponseDto
import com.gradation.lift.network.data.TestJsonDataGenerator.Picture.routineSetPictureResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.userProfilePictureResponseJson
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.PictureService
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
    fun testGetUserProfilePictureService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(userProfilePictureResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = pictureService.getUserProfilePicture()

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/picture/user-profile/")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getUserProfilePictureResponseDto)
    }


    @Test
    fun testGetRoutineSetPictureService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(routineSetPictureResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = pictureService.getRoutineSetPicture()

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/picture/routine-set/")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getRoutineSetPictureResponseDto)
    }
}