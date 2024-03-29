package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.Routine.GetRoutine.GET_ROUTINE_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutine.GET_ROUTINE_SET_ROUTINE_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByLabel.GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByRecent.GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByRoutineSetId.GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Routine.GetRoutineSetRoutineByWeekday.GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_DTO
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateUsedRoutineSet
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.routine.RoutineRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRoutineRemoteDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    RoutineRemoteDataSource {
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

    override suspend fun updateUsedRoutineSet(updateUsedRoutineSet: UpdateUsedRoutineSet): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_ROUTINE_RESPONSE_DTO.toDomain()))
        }
    }

    override suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> {
                emit(
                    NetworkResult.Success(
                        data = GET_ROUTINE_SET_ROUTINE_RESPONSE_DTO.toDomain()
                    )
                )
            }
        }
    }

    override suspend fun getRoutineSetRoutineByRecent(): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = GET_ROUTINE_SET_ROUTINE_BY_RECENT_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: Set<Weekday>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = GET_ROUTINE_SET_ROUTINE_BY_WEEKDAY_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun getRoutineSetRoutineByLabel(label: Set<Label>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = GET_ROUTINE_SET_ROUTINE_BY_LABEL_RESPONSE_DTO.toDomain()))
        }
    }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = GET_ROUTINE_SET_ROUTINE_BY_ROUTINE_SET_ID_RESPONSE_DTO.toDomain()))
            }
        }


}