package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Work.GetPopularWorkCategory.GET_POPULAR_WORK_CATEGORY_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Work.GetRecommendWorkCategory.GET_RECOMMEND_WORK_CATEGORY_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Work.GetWorkCategory.GET_WORK_CATEGORY_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Work.GetWorkCategoryById.GET_WORK_CATEGORY_BY_ID_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Work.GetWorkCategoryByWorkPart.GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Work.GetWorkPart.GET_WORK_PART_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_POPULAR_WORK_CATEGORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_RECOMMEND_WORK_CATEGORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_WORK_CATEGORY_BY_ID_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_WORK_CATEGORY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Work.GET_WORK_PART_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class WorkServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var workService: WorkService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        workService = TestServiceModule.testWorkService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getWorkPartService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_PART_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getWorkPart()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/work/work-part")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_WORK_PART_RESPONSE_DTO)
    }

    @Test
    fun getWorkCategoryService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_CATEGORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getWorkCategory()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/work/work-category")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_WORK_CATEGORY_RESPONSE_DTO)
    }

    @Test
    fun getWorkCategoryByWorkCategoryIdService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_CATEGORY_BY_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getWorkCategoryById(FAKE_INT_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/work/work-category-by-id?work_category_id=$FAKE_INT_DATA")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_WORK_CATEGORY_BY_ID_RESPONSE_DTO)
    }


    @Test
    fun getPopularWorkCategoryService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_POPULAR_WORK_CATEGORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getPopularWorkCategory()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/work/work-category/popular")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_POPULAR_WORK_CATEGORY_RESPONSE_DTO)
    }

    @Test
    fun getRecommendWorkCategoryService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_RECOMMEND_WORK_CATEGORY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getRecommendWorkCategory()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/work/work-category/recommend")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_RECOMMEND_WORK_CATEGORY_RESPONSE_DTO)
    }


    @Test
    fun getWorkCategoryByWorkPartService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getWorkCategoryByWorkPart(FAKE_STRING_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/work/work-category-by-work-part?work_part=${FAKE_STRING_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_DTO)
    }
}