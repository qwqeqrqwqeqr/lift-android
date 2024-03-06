package com.gradation.lift.database.datasource.workPart

import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

/**
 * [getAllWorkPart] 모든 운동 부위 불러오기
 * [fetch] 패치
 * @since 2024-03-06 17:01:14
 */
interface WorkPartDataSource {


    fun getAllWorkPart(): Flow<List<WorkPart>>

    suspend fun fetch(workPart: List<WorkPart>)

}