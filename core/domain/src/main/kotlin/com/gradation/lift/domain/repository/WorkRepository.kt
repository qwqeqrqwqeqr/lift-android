package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.work.Work
import kotlinx.coroutines.flow.Flow

/**
 * [WorkRepository]
 * 현재 진행중인 운동 관련 저장소
 * @since 2023-08-28 20:02:07
 */
interface WorkRepository {


    /**
     * [loadWork]
     * 진행중인 운동을 불러옴
     * @since2024-03-07 12:44:47
     */
    fun loadWork(): Flow<DataState<Work>>

    /**
     * [fetchWork]
     * 운동 정보를 패치함
     * @since 2024-03-07 12:44:47
     */
    fun fetchWork(work: Work): Flow<DataState<Unit>>


    /**
     * [clearWork]
     * 모든 운동 정보를 삭제함
     * @since 2024-01-16 22:22:09
     */
    fun clearWork(): Flow<DataState<Unit>>

    /**
     * [existWork]
     * 진행중인 운동이 존재하는지 확인
     * 2023-08-28 20:06:25
     */
    fun existWork(): Flow<DataState<Boolean>>
}