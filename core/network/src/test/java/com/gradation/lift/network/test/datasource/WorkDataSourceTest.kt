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
import com.gradation.lift.network.datasource.work.DefaultWorkDataSource
import com.gradation.lift.network.datasource.work.WorkDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.WorkService
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
class WorkDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var workService: WorkService
    private lateinit var workDataSource: WorkDataSource
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
        workService = TestServiceModule.testWorkService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        workDataSource = DefaultWorkDataSource(
            workService = workService,
            networkResultHandler = networkResultHandler
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getWorkPartDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.WorkPart.workPartResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            workDataSource.getWorkPart().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.WorkPart.workPartModelList),
            ).isEqualTo(this)
        }
    }


    @Test
    fun getWorkCategoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.WorkCategory.workCategoryResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            workDataSource.getWorkCategory().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.WorkCategory.workCategoryModelList),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getPopularWorkCategoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.WorkCategory.workCategoryResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            workDataSource.getPopularWorkCategory().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.WorkCategory.workCategoryModelList),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getWorkCategoryByWorkPartDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.WorkCategory.workCategoryResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            workDataSource.getWorkCategoryByWorkPart(DefaultDataGenerator.FAKE_STRING_DATA).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.WorkCategory.workCategoryModelList),
            ).isEqualTo(this)
        }
    }


}