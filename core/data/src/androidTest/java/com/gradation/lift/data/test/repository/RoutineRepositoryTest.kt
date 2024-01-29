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
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.updateRoutineSetCountModel
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.updateRoutineSetRoutineModel
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
            successRepository.createRoutineSetRoutine(createRoutineSetRoutine = ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel)
                .first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.createRoutineSetRoutine(createRoutineSetRoutine = ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel)
                .first()
        )
    }


    @Test
    fun updateRoutineSetRoutine() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.updateRoutineSetRoutine(updateRoutineSetRoutine = updateRoutineSetRoutineModel)
                .first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.updateRoutineSetRoutine(updateRoutineSetRoutine = updateRoutineSetRoutineModel)
                .first()
        )
    }

    @Test
    fun updateRoutineSetCount() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.updateRoutineSetCount(updateRoutineSetCount = updateRoutineSetCountModel)
                .first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.updateRoutineSetCount(updateRoutineSetCount = updateRoutineSetCountModel)
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
            successRepository.getRoutineSetRoutineByWeekday(
                setOf(
                    Weekday.Monday(),
                    Weekday.Tuesday()
                )
            ).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByWeekday(setOf(Weekday.Monday(), Weekday.Tuesday()))
                .first()
        )
    }

    @Test
    fun getRoutineSetRoutineByLabel() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList)
        ).isEqualTo(
            successRepository.getRoutineSetRoutineByLabel(setOf(Label.LABEL1, Label.LABEL2)).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByLabel(setOf(Label.LABEL1, Label.LABEL2)).first()
        )
    }

    @Test
    fun getRoutineSetRoutineByRoutineSetId() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList)
        ).isEqualTo(
            successRepository.getRoutineSetRoutineByRoutineSetId(setOf(12, 13, 14)).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetRoutineByRoutineSetId(setOf(12, 13, 14)).first()
        )
    }

}