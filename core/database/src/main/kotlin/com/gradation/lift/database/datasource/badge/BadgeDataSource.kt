package com.gradation.lift.database.datasource.badge

import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.coroutines.flow.Flow

/**
 * [getAllBadge] 모든 뱃지 조회
 * [getAllUserBadge] 사용자에 대한 뱃지 조회
 * [deleteAllBadge] 모든 뱃지 삭제
 * [deleteAllUserBadge] 사용자에 대한 뱃지 삭제
 * [insertAllBadge] 뱃지 추가
 * [insertAllUserBadge] 사용자에 대한 뱃지 추가
 * @since 2023-10-15 18:20:14
 */
interface BadgeDataSource {
    suspend fun getAllBadge(): Flow<List<Badge>>
    suspend fun getAllUserBadge(): Flow<List<UserBadge>>
    suspend fun deleteAllBadge()
    suspend fun deleteAllUserBadge()

    suspend fun insertAllBadge(badge: List<Badge>)
    suspend fun insertAllUserBadge(userBadge: List<UserBadge>)


    suspend fun fetchBadge(badge: List<Badge>)
    suspend fun fetchUserBadge(userBadge: List<UserBadge>)
}