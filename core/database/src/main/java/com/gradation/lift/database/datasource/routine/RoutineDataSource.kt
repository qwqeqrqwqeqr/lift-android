package com.gradation.lift.database.datasource.routine

import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow

/**
 * [getAllRoutineSetRoutine] 모든 루틴 불러오기
 * [deleteAllRoutineSetRoutine] 모든 루틴 삭제
 * [insertAllRoutine] 모든 루틴 추가
 * @since 2023-10-15 18:33:37
 */
interface RoutineDataSource {

    suspend fun getAllRoutineSetRoutine(): Flow<List<RoutineSetRoutine>>
    suspend fun deleteAllRoutineSetRoutine()
    suspend fun insertAllRoutine(
        routineSetRoutine: List<RoutineSetRoutine>,
    )

    suspend fun fetch(routineSetRoutine: List<RoutineSetRoutine>)
}
