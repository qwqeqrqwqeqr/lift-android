package com.gradation.lift.network.fake

import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.getRoutineResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineByRoutineSetIdResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineByWeekdayResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSetRoutine.getRoutineSetRoutineResponseDto
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRoutineDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    RoutineDataSource {
    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> emit(NetworkResult.Success(data = getRoutineResponseDto.toDomain()))
        }
    }

    override suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> {
                emit(
                    NetworkResult.Success(
                        data = getRoutineSetRoutineResponseDto.toDomain()
                    )
                )
            }
        }
    }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
                TestReturnState.Success -> emit(NetworkResult.Success(data = getRoutineSetRoutineByRoutineSetIdResponseDto.toDomain()))
            }
        }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
                TestReturnState.Success -> emit(NetworkResult.Success(data = getRoutineSetRoutineByWeekdayResponseDto.toDomain()))
            }
        }


}