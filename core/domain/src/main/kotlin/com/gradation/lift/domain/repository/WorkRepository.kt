package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.work.Work
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

/**
 * [WorkRepository]
 * 운동 부위 운동 카테고리 그리고 운동 자체와 관련한 저장소
 * [Work] 모델은 [Routine] 을 통해서 만들어 지며, [History]로 저장됨
 * @since 2023-08-28 20:02:07
 */
interface WorkRepository {

    /**
     * [getWorkPart]
     * 모든 운동 부위를 불러옴
     * @since 2023-08-28 20:02:29
     */
    fun getWorkPart(): Flow<DataState<List<WorkPart>>>

    /**
     * [getWorkCategory]
     * 모든 운동 카테고리를 불러옴
     * @since 2023-08-28 20:02:51
     */
    fun getWorkCategory(): Flow<DataState<List<WorkCategory>>>

    /**
     * [getWorkCategoryById]
     * 모든 운동 카테고리를 불러옴
     * @since 2023-12-08 12:25:30
     */
    fun getWorkCategoryById(workCategoryId: Int): Flow<DataState<WorkCategory>>


    /**
     * [getWorkCategoryByWorkPart]
     * 운동 부위에 따른 운동 카테고리를 불러옴
     * @since 2023-08-28 20:03:42
     */
    fun getWorkCategoryByWorkPart(workPart: String): Flow<DataState<List<WorkCategory>>>

    /**
     * [getPopularWorkCategory]
     * 인기 있는 운동 종목들을 불러옵니다.
     * @since 2023-09-25 17:35:38
     */
    fun getPopularWorkCategory(): Flow<DataState<List<WorkCategory>>>

    /**
     * [getRecommendWorkCategory]
     * 추천 운동 종목들을 불러옵니다.
     * @since 2023-10-06 22:26:50
     */
    fun getRecommendWorkCategory(): Flow<DataState<List<WorkCategory>>>

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