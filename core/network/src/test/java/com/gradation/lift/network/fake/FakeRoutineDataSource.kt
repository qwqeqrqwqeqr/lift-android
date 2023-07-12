package com.gradation.lift.network.fake

import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.data.TestRoutineDataGenerator.getRoutineResponseDto
import com.gradation.lift.network.data.TestRoutineDataGenerator.getRoutineSetRoutineByRoutineSetIdResponseDto
import com.gradation.lift.network.data.TestRoutineDataGenerator.getRoutineSetRoutineByWeekdayResponseDto
import com.gradation.lift.network.data.TestRoutineDataGenerator.getRoutineSetRoutineResponseDto
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_BOOLEAN_DATA
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRoutineDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    RoutineDataSource {
    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(APIResult.Fail("오류"))
                TestReturnState.Success -> emit(APIResult.Success(data = FAKE_BOOLEAN_DATA))
            }
        }

    override suspend fun getRoutine(): Flow<APIResult<List<Routine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = getRoutineResponseDto.toRoutine()))
        }
    }

    override suspend fun getRoutineSetRoutine(): Flow<APIResult<List<RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = getRoutineSetRoutineResponseDto.toRoutineSetRoutine()))
        }
    }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: String): Flow<APIResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(APIResult.Fail("오류"))
                TestReturnState.Success -> emit(APIResult.Success(data = getRoutineSetRoutineByRoutineSetIdResponseDto.toRoutineSetRoutine()))
            }
        }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetId: Int): Flow<APIResult<List<RoutineSetRoutine>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(APIResult.Fail("오류"))
                TestReturnState.Success -> emit(APIResult.Success(data = getRoutineSetRoutineByWeekdayResponseDto.toRoutineSetRoutine()))
            }
        }


}