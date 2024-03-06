package com.gradation.lift.database.datasource.badge

import com.gradation.lift.model.model.badge.Badge
import kotlinx.coroutines.flow.Flow

/**
 * [getAllBadge] 모든 뱃지 조회
 * [fetch] 패치
 * @since 2024-03-06 16:49:00
 */
interface BadgeDataSource {
    fun getAllBadge(): Flow<List<Badge>>
    suspend fun fetch(badge: List<Badge>)
}