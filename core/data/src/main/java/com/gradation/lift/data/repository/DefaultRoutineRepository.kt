package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.*
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.RoutineDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject


class DefaultRoutineRepository @Inject constructor(
    private val routineDataSource: RoutineDataSource,
) : RoutineRepository {


    override  fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine) : Flow<DataState<Boolean>> = flow{
        routineDataSource.createRoutineSet(createRoutineSetRoutine).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutine(): Flow<DataState<List<Routine>>> = flow{
        routineDataSource.getRoutine().collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>> = flow{
        routineDataSource.getRoutineSetRoutine().collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<DataState<List<RoutineSetRoutine>>> = flow{
        routineDataSource.getRoutineSetRoutineByWeekday(weekday).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: List<Int>): Flow<DataState<List<RoutineSetRoutine>>> = flow{
        routineDataSource.getRoutineSetRoutineByRoutineSetId(routineSetIdList).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }


}