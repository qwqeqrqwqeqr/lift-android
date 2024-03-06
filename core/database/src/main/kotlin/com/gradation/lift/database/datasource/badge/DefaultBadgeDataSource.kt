package com.gradation.lift.database.datasource.badge

import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.badge.Badge
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultBadgeDataSource @Inject constructor(
    private val badgeDao: BadgeDao,
) : BadgeDataSource {
    override fun getAllBadge(): Flow<List<Badge>> = flow {
        badgeDao.getAllBadge().collect { badgeEntityList ->
            emit(badgeEntityList.map { it.toDomain() })
        }
    }

    override suspend fun fetch(badge: List<Badge>) {
        badgeDao.deleteAllBadge()
        badgeDao.insertAllBadge(*badge.map { it.toEntity() }.toTypedArray())
    }


}