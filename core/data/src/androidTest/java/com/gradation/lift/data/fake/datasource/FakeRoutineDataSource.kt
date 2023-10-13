package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.Routine.getRoutineResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineByRoutineSetIdResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineByWeekdayResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineResponseDto
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRoutineDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    RoutineDataSource {
    override suspend fun createRoutineSetRoutine(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun deleteRoutineSetRoutine(routineSetId: Int): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun updateRoutineSetRoutine(updateRoutineSetRoutine: UpdateRoutineSetRoutine): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = getRoutineResponseDto.toDomain()))
        }
    }

    override suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> {
                emit(
                    NetworkResult.Success(
                        data = getRoutineSetRoutineResponseDto.toDomain()
                    )
                )
            }
        }
    }

    override suspend fun getRoutineSetRoutineByWeekday(label: List<Any>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = getRoutineSetRoutineByRoutineSetIdResponseDto.toDomain()))
            }
        }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = getRoutineSetRoutineByWeekdayResponseDto.toDomain()))
            }
        }


}