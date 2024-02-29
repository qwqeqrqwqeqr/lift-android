package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakeRoutineDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.CREATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.UPDATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Weekday.WEEKDAY_MODEL
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.routine.RoutineDataSource
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
        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.createRoutineSetRoutine(createRoutineSetRoutine = CREATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )

        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.createRoutineSetRoutine(createRoutineSetRoutine = CREATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )
    }

    @Test
    fun updateRoutineSetDataSource() = runTest {
        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.updateRoutineSetRoutine(updateRoutineSetRoutine = UPDATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )

        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.updateRoutineSetRoutine(updateRoutineSetRoutine = UPDATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )
    }

    @Test
    fun deleteRoutineSetDataSource() = runTest {
        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.deleteRoutineSetRoutine(FAKE_INT_DATA).first()
        )

        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.deleteRoutineSetRoutine(FAKE_INT_DATA).first()
        )
    }


    @Test
    fun getRoutineDataSource() = runTest {
        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(listOf(ROUTINE_MODEL)),
            dataSource.getRoutine().first()
        )

        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutine().first()
        )
    }

    @Test
    fun getRoutineSetRoutineDataSource() = runTest {
        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            dataSource.getRoutineSetRoutine().first()
        )

        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutineSetRoutine().first()
        )
    }

    @Test
    fun getRoutineSetRoutineByWeekdayDataSource() = runTest {
        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            dataSource.getRoutineSetRoutineByWeekday(setOf(WEEKDAY_MODEL)).first()
        )

        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutineSetRoutineByWeekday(setOf(WEEKDAY_MODEL)).first()
        )
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetIdDataSource() = runTest {
        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            NetworkResult.Success(listOf(ROUTINE_SET_ROUTINE_MODEL)),
            dataSource.getRoutineSetRoutineByRoutineSetId(setOf(FAKE_INT_DATA)).first()
        )

        dataSource =
            FakeRoutineDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutineSetRoutineByRoutineSetId(setOf(FAKE_INT_DATA)).first()
        )
    }





}