package com.gradation.lift.network.datasource.badge

import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BadgeDataSource {


    suspend fun getBadge(): Flow<NetworkResult<List<Badge>>>
    suspend fun getUserBadge(): Flow<NetworkResult<List<UserBadge>>>
    suspend fun createUserBadge(createUserBadge: CreateUserBadge): Flow<NetworkResult<Unit>>
    suspend fun getUserBadgeByMainFlag(): Flow<NetworkResult<List<UserBadge>>>
    suspend fun getUserBadgeByCondition(): Flow<NetworkResult<BadgeCondition>>

    suspend fun updateUserBadgeMainFlag(updateUserBadgeMainFlag: UpdateUserBadgeMainFlag): Flow<NetworkResult<Unit>>

}