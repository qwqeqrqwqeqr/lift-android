package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.WorkCategory.getWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.WorkCategory.getWorkCategoryResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.WorkPart.getWorkPartResponseDto
import com.gradation.lift.network.data.TestJsonDataGenerator.WorkCategory.workCategoryResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.WorkPart.workPartResponseJson
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.WorkService
import com.gradation.lift.model.data_generator.DefaultDataGenerator.FAKE_STRING_DATA
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
    fun testGetWorkPartService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(workPartResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getWorkPart()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/work/work-part/")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getWorkPartResponseDto)
    }

    @Test
    fun testGetWorkCategoryService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(workCategoryResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getWorkCategory()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/work/work-category/")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getWorkCategoryResponseDto)
    }


    @Test
    fun testGetWorkCategoryByWorkPartService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(workCategoryResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = workService.getWorkCategoryByWorkPart(FAKE_STRING_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/work/work-category-by-work-part/?work_part=${FAKE_STRING_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getWorkCategoryByWorkPartResponseDto)
    }
}