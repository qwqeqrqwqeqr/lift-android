package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow


/**
 *  [FavoriteRepository]
 *  관심등록을 관리하는 저장소
 *  @since 2024-02-23 15:53:01
 */
interface FavoriteRepository {

    /**
     * [getWorkCategoryFavorite]
     * 사용자가 관심 등록한 운동 카테고리 불러오기
     * @since 2024-02-23 15:53:43
     */
    fun getWorkCategoryFavorite(): Flow<DataState<List<Int>>>

    /**
     * [updateWorkCategoryFavorite]
     * 사용자가 관심 등록한 운동 카테고리 업데이트하기 (on/off 방식)
     * @since 2024-02-23 15:53:47
     */
    fun updateWorkCategoryFavorite(workCategoryId: Int): Flow<DataState<Unit>>

}