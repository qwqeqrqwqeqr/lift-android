package com.gradation.lift.network.datasource.routine

import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateUsedRoutineSet
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [RoutineRemoteDataSource]
 * 루틴과 관련한 데이터 소스
 * @since 2023-09-06 16:20:37
 */
interface RoutineRemoteDataSource {

    suspend fun createRoutineSetRoutine(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<NetworkResult<Unit>>
    suspend fun deleteRoutineSetRoutine(routineSetId: Int): Flow<NetworkResult<Unit>>
    suspend fun updateRoutineSetRoutine(updateRoutineSetRoutine: UpdateRoutineSetRoutine): Flow<NetworkResult<Unit>>
    suspend fun updateUsedRoutineSet(updateUsedRoutineSet: UpdateUsedRoutineSet): Flow<NetworkResult<Unit>>


    suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>>

    suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>>
    suspend fun getRoutineSetRoutineByRecent(): Flow<NetworkResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByWeekday(weekday: Set<Weekday>): Flow<NetworkResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByLabel(label: Set<Label>): Flow<NetworkResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<NetworkResult<List<RoutineSetRoutine>>>


}


