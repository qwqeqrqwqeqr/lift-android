package com.gradation.lift.database.datasource.work

import com.gradation.lift.model.model.work.Work
import kotlinx.coroutines.flow.Flow

/**
 * [WorkDataSource]
 * [load] 운동 불러오기
 * [existWork] 운동 존재 여부 확인
 * [fetch] 패치
 * [clear] 운동 테이블 초기화
 * @since 2024-03-06 17:14:09
 */
interface WorkDataSource {

    fun load(): Flow<Work>

    fun existWork(): Flow<Boolean>
    suspend fun fetch(work: Work)

    suspend fun clear()

}