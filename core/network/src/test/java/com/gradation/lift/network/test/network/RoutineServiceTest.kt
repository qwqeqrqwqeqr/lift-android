package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestRoutineDtoDataGenerator
import com.gradation.lift.network.data.TestRoutineDtoDataGenerator.createRoutineSetRequestDto
import com.gradation.lift.network.data.TestUserDtoDataGenerator
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.RoutineService
import com.gradation.lift.network.service.UserService
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA
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
class RoutineServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var routineService: RoutineService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        routineService = TestServiceModule.testRoutineService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun testCreateRoutineSetService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestRoutineDtoDataGenerator.resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response = routineService.createRoutineSet(createRoutineSetRequestDto = createRoutineSetRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set/")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(TestRoutineDtoDataGenerator.createRoutineSetResponseDto)
    }


    @Test
    fun testGetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestRoutineDtoDataGenerator.routineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutine()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine/")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(TestRoutineDtoDataGenerator.getRoutineResponseDto)
    }


    @Test
    fun testGetRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestRoutineDtoDataGenerator.routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutine()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine/")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(TestRoutineDtoDataGenerator.getRoutineSetRoutineResponseDto)
    }


    @Test
    fun testGetRoutineSetRoutineByWeekdayService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestRoutineDtoDataGenerator.routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByWeekday(FAKE_STRING_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine-by-weekday/?weekday=${FAKE_STRING_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(TestRoutineDtoDataGenerator.getRoutineSetRoutineByWeekdayResponseDto)
    }

    @Test
    fun testGetRoutineSetRoutineByRoutineSetIdService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestRoutineDtoDataGenerator.routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByRoutineSetId(FAKE_INT_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine-by-routine-set-id/?routine_set_id=${FAKE_INT_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(TestRoutineDtoDataGenerator.getRoutineSetRoutineByRoutineSetIdResponseDto)
    }

}