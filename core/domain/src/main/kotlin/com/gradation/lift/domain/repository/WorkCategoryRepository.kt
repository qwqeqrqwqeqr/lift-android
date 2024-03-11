package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.coroutines.flow.Flow

/**
 * [WorkCategoryRepository]
 * 운동 카테고리 저장소
 * @since 2024-03-06 17:27:10
 */
interface WorkCategoryRepository {

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

}