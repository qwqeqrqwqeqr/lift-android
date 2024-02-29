package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LABEL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEEKDAY_DATA
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.CreateRoutineSetRoutine.CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.CreateRoutineSetRoutine.CREATE_ROUTINE_SET_ROUTINE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.DeleteRoutineSetRoutine.DELETE_ROUTINE_SET_ROUTINE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.GetRoutine.GET_ROUTINE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutine.GET_ROUTINE_SET_ROUTINE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByLabel.GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByRecent.GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByRoutineSetId.GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByWeekday.GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.UpdateRoutineSetRoutine.UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.UpdateRoutineSetRoutine.UPDATE_ROUTINE_SET_ROUTINE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.UpdateUsedRoutineSet.UPDATE_USED_ROUTINE_SET_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.UpdateUsedRoutineSet.UPDATE_USED_ROUTINE_SET_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.CREATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.DELETE_ROUTINE_SET_ROUTINE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.GET_ROUTINE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.GET_ROUTINE_SET_ROUTINE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.UPDATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Routine.UPDATE_USED_ROUTINE_SET_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.RoutineService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


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
    fun getRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutine()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_ROUTINE_RESPONSE_DTO)
    }


    @Test
    fun getRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutine()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_ROUTINE_SET_ROUTINE_RESPONSE_DTO)
    }


    @Test
    fun updateRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response =
            routineService.updateRoutineSetRoutine(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine")
        Truth.assertThat(request.method).isEqualTo(Constants.PUT)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_RESPONSE_DTO)
    }


    @Test
    fun createRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response =
            routineService.createRoutineSetRoutine(CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine")
        Truth.assertThat(request.method).isEqualTo(Constants.POST)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(CREATE_ROUTINE_SET_ROUTINE_RESPONSE_DTO)
    }


    @Test
    fun deleteRoutineSetRoutineService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(DELETE_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response = routineService.deleteRoutineSetRoutine(routineSetId = FAKE_INT_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine?routine_set_id=${FAKE_INT_DATA}")
        Truth.assertThat(request.method).isEqualTo(Constants.DELETE)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(DELETE_ROUTINE_SET_ROUTINE_RESPONSE_DTO)
    }

    @Test
    fun updateUsedRoutineSetService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USED_ROUTINE_SET_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        val response =
            routineService.updateUsedRoutineSet(UPDATE_USED_ROUTINE_SET_REQUEST_DTO)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set/use")
        Truth.assertThat(request.method).isEqualTo(Constants.PATCH)

        Truth.assertThat(response.code()).isEqualTo(Constants.CREATED)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(UPDATE_USED_ROUTINE_SET_RESPONSE_DTO)
    }


    @Test
    fun getRoutineSetRoutineByRecentService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByRecent()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path).isEqualTo("/routine/routine-set-routine/recent")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_DTO)
    }


    @Test
    fun getRoutineSetRoutineByWeekdayService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByWeekday(FAKE_WEEKDAY_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine/weekday?weekday=$FAKE_WEEKDAY_DATA")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_DTO)
    }

    @Test
    fun getRoutineSetRoutineByLabelService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByLabel(FAKE_LABEL_DATA)
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine/label?label=$FAKE_LABEL_DATA")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_DTO)
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetIdService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        val response = routineService.getRoutineSetRoutineByRoutineSetId(FAKE_INT_DATA.toString())
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.path)
            .isEqualTo("/routine/routine-set-routine/routine-set-id?routine_set_id_list=$FAKE_INT_DATA")
        Truth.assertThat(request.method).isEqualTo(Constants.GET)

        Truth.assertThat(response.code()).isEqualTo(Constants.OK)
        Truth.assertThat(response.body()).isInstanceOf(APIResultWrapper::class.java)
        Truth.assertThat(response.body()!!.data)
            .isEqualTo(GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_DTO)
    }

}