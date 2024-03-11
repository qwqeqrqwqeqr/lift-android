package com.gradation.lift.database.datasource.userBadge

import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.coroutines.flow.Flow

/**
 * [getAllUserBadge] 사용자에 대한 뱃지 조회
 * [fetch] 사용자에 대한 뱃지 삭제
 * @since 2024-03-06 16:37:27
 */
interface UserBadgeLocalDataSource {
    fun getAllUserBadge(): Flow<List<UserBadge>>
    suspend fun fetch(userBadge: List<UserBadge>)
    suspend fun updateUserBadgeMainFlag(updateUserBadgeMainFlag: UpdateUserBadgeMainFlag)
}