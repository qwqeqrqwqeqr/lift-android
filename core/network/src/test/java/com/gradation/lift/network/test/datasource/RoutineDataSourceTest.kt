package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator
import com.gradation.lift.network.datasource.routine.DefaultRoutineDataSource
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
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
            networkResultHandler = networkResultHandler
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
                .setBody(TestJsonDataGenerator.Common.resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.createRoutineSetRoutine(createRoutineSetRoutine = ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel)
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
                .setBody(TestJsonDataGenerator.Common.resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.updateRoutineSetRoutine(updateRoutineSetRoutine = ModelDataGenerator.RoutineSetRoutine.updateRoutineSetRoutineModel)
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
                .setBody(TestJsonDataGenerator.Common.resultResponseJson)
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
    fun getRoutineDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.Routine.routineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutine().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.Routine.routineModelList),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRoutineSetRoutineDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.RoutineSetRoutine.routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutine().first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRoutineSetRoutineByWeekdayDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.RoutineSetRoutine.routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutineByWeekday(Weekday.Monday()).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList),
            ).isEqualTo(this)
        }
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetIdDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestJsonDataGenerator.RoutineSetRoutine.routineSetRoutineResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            routineDataSource.getRoutineSetRoutineByRoutineSetId(setOf(12, 13, 14)).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList),
            ).isEqualTo(this)
        }
    }


}