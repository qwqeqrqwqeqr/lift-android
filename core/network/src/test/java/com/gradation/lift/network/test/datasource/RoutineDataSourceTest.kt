package com.gradation.lift.network.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.fake.FakeRoutineDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.routineModelList
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class RoutineDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: RoutineDataSource



    @Test
    fun createRoutineSetDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.createRoutineSet(createRoutineSetRoutine =  createRoutineSetRoutineModel).first()
        )

        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.createRoutineSet(createRoutineSetRoutine =  createRoutineSetRoutineModel).first()
        )
    }

    @Test
    fun getRoutineDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(routineModelList),
            dataSource.getRoutine().first()
        )

        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutine().first()
        )
    }

    @Test
    fun getRoutineSetRoutineDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutine().first()
        )

        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutineSetRoutine().first()
        )
    }

    @Test
    fun getRoutineSetRoutineByWeekdayDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutineByWeekday(Weekday.Monday()).first()
        )

        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutineSetRoutineByWeekday(Weekday.Monday()).first()
        )
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetIdDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutineByRoutineSetId(setOf(12,13,14)).first()
        )

        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutineSetRoutineByRoutineSetId(setOf(12,13,14)).first()
        )
    }





}