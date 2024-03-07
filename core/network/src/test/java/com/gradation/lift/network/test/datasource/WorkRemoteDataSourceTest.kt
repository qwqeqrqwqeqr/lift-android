package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.WORK_CATEGORY_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.WorkPart.WORK_PART_MODEL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_POPULAR_WORK_CATEGORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_RECOMMEND_WORK_CATEGORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_WORK_CATEGORY_BY_ID_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_WORK_CATEGORY_RESPONSE_JSON
import com.gradation.lift.network.datasource.work.DefaultWorkRemoteDataSource
import com.gradation.lift.network.datasource.work.WorkRemoteDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class WorkRemoteDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var workService: WorkService
    private lateinit var workRemoteDataSource: WorkRemoteDataSource
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
        workRemoteDataSource = DefaultWorkRemoteDataSource(
            workService = workService,
            networkResultHandler = networkResultHandler, dispatcher
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
                .setBody(TestJsonDataGenerator.Work.GET_WORK_PART_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )
        with(
            workRemoteDataSource.getWorkPart().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(WORK_PART_MODEL)),
            ).isEqualTo(this)
        }
    }


    @Test
    fun getWorkCategoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_CATEGORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            workRemoteDataSource.getWorkCategory().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(WORK_CATEGORY_MODEL)),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getWorkCategoryByIdDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_CATEGORY_BY_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            workRemoteDataSource.getWorkCategoryById(FAKE_INT_DATA).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(WORK_CATEGORY_MODEL),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getPopularWorkCategoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_POPULAR_WORK_CATEGORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            workRemoteDataSource.getPopularWorkCategory().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(WORK_CATEGORY_MODEL)),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRecommendWorkCategoryDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_RECOMMEND_WORK_CATEGORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            workRemoteDataSource.getRecommendWorkCategory().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(WORK_CATEGORY_MODEL)),
            ).isEqualTo(this)
        }
    }


    @Test
    fun getWorkCategoryByWorkPartDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(
            workRemoteDataSource.getWorkCategoryByWorkPart(DefaultDataGenerator.FAKE_STRING_DATA)
                .first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(WORK_CATEGORY_MODEL)),
            ).isEqualTo(this)
        }
    }


}