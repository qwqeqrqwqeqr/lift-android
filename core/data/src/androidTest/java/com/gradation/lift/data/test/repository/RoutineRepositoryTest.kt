package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.di.TestDispatcher.testDispatchers
import com.gradation.lift.data.fake.datasource.FakeRoutineDataSource
import com.gradation.lift.data.repository.DefaultRoutineRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.CREATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.LABEL_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.UPDATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.UPDATE_USED_ROUTINE_SET_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Weekday.WEEKDAY_MODEL
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest

class RoutineRepositoryTest {

    private lateinit var failDataSource: RoutineDataSource
    private lateinit var successDataSource: RoutineDataSource
    private lateinit var successRepository: RoutineRepository
    private lateinit var failRepository: RoutineRepository
    private val dispatcher: DispatcherProvider = testDispatchers()

    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakeRoutineDataSource(TestReturnState.Fail)
        successDataSource = FakeRoutineDataSource(TestReturnState.Success)

        successRepository = DefaultRoutineRepository(successDataSource,dispatcher)
        failRepository = DefaultRoutineRepository(failDataSource,dispatcher)
    }


    @Test
    fun createRoutineSetRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.createRoutineSetRoutine(createRoutineSetRoutine = CREATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.createRoutineSetRoutine(createRoutineSetRoutine = CREATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )
    }


    @Test
    fun updateRoutineSetRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.updateRoutineSetRoutine(updateRoutineSetRoutine = UPDATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.updateRoutineSetRoutine(updateRoutineSetRoutine = UPDATE_ROUTINE_SET_ROUTINE_MODEL)
                .first()
        )
    }

    @Test
    fun updateUsedRoutineSet() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.updateUsedRoutineSet(updateUsedRoutineSet = UPDATE_USED_ROUTINE_SET_MODEL)
                .first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.updateUsedRoutineSet(updateUsedRoutineSet = UPDATE_USED_ROUTINE_SET_MODEL)
                .first()
        )
    }

    @Test
    fun deleteRoutineSetRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.deleteRoutineSetRoutine(FAKE_INT_DATA).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.deleteRoutineSetRoutine(FAKE_INT_DATA).first()
        )
    }

    @Test
    fun getRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(ROUTINE_MODEL))
        ).isEqualTo(
            successRepository.getRoutine().first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutine().first()
        )
    }

    @Test
    fun getRoutineSetRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(ROUTINE_SET_ROUTINE_MODEL))
        ).isEqualTo(
            successRepository.getRoutineSetRoutine().first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutine().first()
        )
    }

    @Test
    fun getRoutineSetRoutineByWeekday() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(ROUTINE_SET_ROUTINE_MODEL))
        ).isEqualTo(
            successRepository.getRoutineSetRoutineByWeekday(
                setOf(WEEKDAY_MODEL)
            ).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByWeekday(setOf(WEEKDAY_MODEL))
                .first()
        )
    }

    @Test
    fun getRoutineSetRoutineByLabel() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(ROUTINE_SET_ROUTINE_MODEL))
        ).isEqualTo(
            successRepository.getRoutineSetRoutineByLabel(setOf(LABEL_MODEL)).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByLabel(setOf(LABEL_MODEL)).first()
        )
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetId() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(ROUTINE_SET_ROUTINE_MODEL))
        ).isEqualTo(
            successRepository.getRoutineSetRoutineByRoutineSetId(setOf(FAKE_INT_DATA)).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByRoutineSetId(setOf(FAKE_INT_DATA)).first()
        )
    }

}