package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.CREATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.UPDATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.UPDATE_USED_ROUTINE_SET_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Weekday.WEEKDAY_MODEL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
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
import com.gradation.lift.network.datasource.routine.DefaultRoutineDataSource
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.RoutineService
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
class RoutineDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var routineService: RoutineService
    private lateinit var routineDataSource: RoutineDataSource
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
        routineService = TestServiceModule.testRoutineService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        routineDataSource = DefaultRoutineDataSource(
            routineService,
            networkResultHandler = networkResultHandler, dispatcher
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun createRoutineSetRoutineDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.createRoutineSetRoutine(createRoutineSetRoutine = CREATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }


    @Test
    fun deleteRoutineSetRoutineDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(DELETE_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.deleteRoutineSetRoutine(FAKE_INT_DATA)
                .first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }


    @Test
    fun updateRoutineSetRoutineDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.updateRoutineSetRoutine(UPDATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }

    @Test
    fun updateUsedRoutineSetDataSource() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USED_ROUTINE_SET_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.updateUsedRoutineSet(updateUsedRoutineSet = UPDATE_USED_ROUTINE_SET_MODEL)
                .first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRoutineDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutine().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(ROUTINE_MODEL)),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRoutineSetRoutineDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutine().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRoutineSetRoutineByRecentDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutine().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRoutineSetRoutineByWeekdayDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutineByWeekday(setOf(WEEKDAY_MODEL)).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            ).isEqualTo(this)
        }
    }


    @Test
    fun getRoutineSetRoutineByLabelDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutineByLabel(setOf(Label.LABEL1, Label.LABEL2)).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            ).isEqualTo(this)
        }
    }


    @Test
    fun getRoutineSetRoutineByRoutineSetIdDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutineByRoutineSetId(setOf(FAKE_INT_DATA)).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            ).isEqualTo(this)
        }
    }


}