package com.gradation.lift.database.datasource

import com.gradation.lift.model.model.work.Work
import kotlinx.coroutines.flow.Flow

/**
 * [WorkDataSource]
 * [insertWork] 현재 진행중인 운동 추가
 * [updateWork] 현재 진행중인 운동 업데이트
 * [deleteWork] 현재 진행중인 운동 삭제
 * [deleteAllWork] 현재 진행중인 운동 삭제
 * [getAllWork] 운동 데이터 전부 지우기
 * [existWork] 현재 진행하고 있는 운동이 존재하는지 확인
 * @since 2023-10-15 18:34:04
 */
interface WorkDataSource {

    suspend fun insertWork(work: Work)
    suspend fun updateWork(work: Work)
    suspend fun deleteWork()
    suspend fun deleteAllWork()
    fun getAllWork(): Flow<List<Work>>
    fun existWork(): Flow<Boolean>
}