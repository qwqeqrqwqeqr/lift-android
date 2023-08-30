package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.Picture.routineSetPictureResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.userProfilePictureResponseJson
import com.gradation.lift.network.datasource.picture.DefaultPictureDataSource
import com.gradation.lift.network.datasource.picture.PictureDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.PictureService
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
class PictureDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var pictureService: PictureService
    private lateinit var pictureDataSource: PictureDataSource
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
        pictureService = TestServiceModule.testPictureService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        pictureDataSource = DefaultPictureDataSource(
            pictureService,
            networkResultHandler = networkResultHandler
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getUserProfilePictureDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(userProfilePictureResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            pictureDataSource.getUserProfilePicture().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.Picture.userProfilePictureModelList)
            ).isEqualTo(this)
        }
    }


    @Test
    fun getRoutineSetPictureDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(routineSetPictureResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(pictureDataSource.getRoutineSetPicture().first()) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.Picture.routineSetPictureModelList)
            ).isEqualTo(this)
        }
    }


}