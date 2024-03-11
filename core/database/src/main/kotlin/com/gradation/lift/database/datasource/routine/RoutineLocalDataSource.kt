package com.gradation.lift.database.datasource.routine

import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow

/**
 * [getAllRoutineSetRoutine] 모든 루틴 불러오기
 * [fetch] 패치
 * @since 2024-03-06 16:58:04
 */
interface RoutineLocalDataSource {

    fun getAllRoutineSetRoutine(): Flow<List<RoutineSetRoutine>>


    suspend fun fetch(routineSetRoutine: List<RoutineSetRoutine>)
}
