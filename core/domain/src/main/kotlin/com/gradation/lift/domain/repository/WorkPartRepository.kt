package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

/**
 * [WorkPartRepository]
 * 운동 부위 저장소
 * @since 2024-03-06 17:27:22
 */
interface WorkPartRepository {

    /**
     * [getWorkPart]
     * 모든 운동 부위를 불러옴
     * @since 2023-08-28 20:02:29
     */
    fun getWorkPart(): Flow<DataState<List<WorkPart>>>

}