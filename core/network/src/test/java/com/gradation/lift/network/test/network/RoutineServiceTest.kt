package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.getId
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.getRoutineResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineByRoutineSetIdResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineByWeekdayResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineResponseDto
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.routineResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.RoutineSetRoutine.routineSetRoutineResponseJson
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.service.RoutineService
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.createRoutineSetRoutineRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.createRoutineSetRoutineResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.deleteRoutineSetRoutineResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.updateRoutineSetCountRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.updateRoutineSetCountResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.updateRoutineSetRoutineRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.updateRoutineSetRoutineResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineByLabelResponseDto
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
    fun createRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response =
            routineService.createRoutineSetRoutine(createRoutineSetRoutineRequestDto = createRoutineSetRoutineRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine/")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(createRoutineSetRoutineResponseDto)
    }

    @Test
    fun updateRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response =
            routineService.updateRoutineSetRoutine(updateRoutineSetRoutineRequestDto = updateRoutineSetRoutineRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine/")
        Truth.assertThat(request.method).isEqualTo(Constants.PUT)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(updateRoutineSetRoutineResponseDto)
    }

    @Test
    fun updateRoutineSetCountService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response =
            routineService.updateRoutineSetCount(updateRoutineSetCountRequestDto = updateRoutineSetCountRequestDto)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set/count/")
        Truth.assertThat(request.method).isEqualTo(Constants.PUT)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(updateRoutineSetCountResponseDto)
    }


    @Test
    fun deleteRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response = routineService.deleteRoutineSetRoutine(routineSetId = FAKE_INT_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine/?routine_set_id=${FAKE_INT_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.DELETE)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(deleteRoutineSetRoutineResponseDto)
    }


    @Test
    fun getRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(routineResponseJson)
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
            .isEqualTo(getRoutineResponseDto)
    }


    @Test
    fun getRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(routineSetRoutineResponseJson)
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
            .isEqualTo(getRoutineSetRoutineResponseDto)
    }


    @Test
    fun getRoutineSetRoutineByWeekdayService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByWeekday("${Weekday.MONDAY_VALUE},${Weekday.TUESDAY_VALUE}")
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine/weekday/?weekday=Mon%2CTue")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getRoutineSetRoutineByWeekdayResponseDto)
    }

    @Test
    fun getRoutineSetRoutineByLabelService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByLabel("${Label.LABEL1.getId()},${Label.LABEL2.getId()}")
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine/label/?label=1%2C2")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getRoutineSetRoutineByLabelResponseDto)
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetIdService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByRoutineSetId("12,13,14")
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine/routine-set-id/?routine_set_id_list=12%2C13%2C14")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(getRoutineSetRoutineByRoutineSetIdResponseDto)
    }

}