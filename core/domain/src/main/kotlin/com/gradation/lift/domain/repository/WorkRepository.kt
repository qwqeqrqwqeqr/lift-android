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
     * [getWork]
     * 진행중인 운동을 불러옴
     * @since 2023-08-28 20:05:30
     */
    fun getWork(): Flow<DataState<Work>>

    /**
     * [createWork]
     * 운동을 생성함
     * @since 2023-08-28 20:05:35
     */
    fun createWork(work: Work): Flow<DataState<Unit>>

    /**
     * [updateWork]
     * 운동을 갱신함
     * @since 2023-08-28 20:05:48
     */
    fun updateWork(work: Work): Flow<DataState<Unit>>

    /**
     * [deleteWork]
     * 운동을 삭제함
     * 2023-08-28 20:06:05
     */
    fun deleteWork(work: Work): Flow<DataState<Unit>>
    /**
     * [deleteAllWork]
     * 모든 운동을 삭제함
     * @since 2024-01-16 22:22:09
     */
    fun deleteAllWork(): Flow<DataState<Unit>>

    /**
     * [existWork]
     * 진행중인 운동이 존재하는지 확인
     * 2023-08-28 20:06:25
     */
    fun existWork(): Flow<DataState<Boolean>>
}