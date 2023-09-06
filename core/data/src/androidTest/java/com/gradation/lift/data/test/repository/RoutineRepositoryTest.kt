package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.fake.datasource.FakeRoutineDataSource
import com.gradation.lift.data.repository.DefaultRoutineRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator
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


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakeRoutineDataSource(TestReturnState.Fail)
        successDataSource = FakeRoutineDataSource(TestReturnState.Success)

        successRepository = DefaultRoutineRepository(successDataSource)
        failRepository = DefaultRoutineRepository(failDataSource)
    }



    @Test
    fun createRoutineSetRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.createRoutineSetRoutine(createRoutineSetRoutine = ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.createRoutineSetRoutine(createRoutineSetRoutine = ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel).first()
        )
    }

    @Test
    fun getRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.Routine.routineModelList)
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
            DataState.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList)
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
            DataState.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList)
        ).isEqualTo(
            successRepository.getRoutineSetRoutineByWeekday(Weekday.Monday()).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByWeekday(Weekday.Monday()).first()
        )
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetId() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList)
        ).isEqualTo(
            successRepository.getRoutineSetRoutineByRoutineSetId(setOf(12,13,14)).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByRoutineSetId(setOf(12,13,14)).first()
        )
    }

}