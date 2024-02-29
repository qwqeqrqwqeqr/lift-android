package com.gradation.lift.network.datasource.favorite

import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [FavoriteDataSource]
 * 인증 인가와 관련된 데이터 소스
 * @since 2023-08-28 22:06:44
 */
interface FavoriteDataSource {

    suspend fun getWorkCategoryFavorite(): Flow<NetworkResult<List<Int>>>
    suspend fun updateWorkCategoryFavorite(workCategoryId: Int): Flow<NetworkResult<Unit>>
}