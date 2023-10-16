package com.gradation.lift.database.datasource.badge

import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultBadgeDataSource @Inject constructor(
    private val badgeDao: BadgeDao,
) : BadgeDataSource {
    override suspend fun getAllBadge(): Flow<List<Badge>> = flow {
        badgeDao.getAllBadge().collect { badgeEntityList ->
            emit(badgeEntityList.map { it.toDomain() })
        }
    }

    override suspend fun getAllUserBadge(): Flow<List<UserBadge>> = flow {
        badgeDao.getAllUserBadge().collect { userBadgeEntityList ->
            emit(userBadgeEntityList.map { it.toDomain() })
        }
    }

    override suspend fun deleteAllBadge() {
        badgeDao.deleteAllBadge()
    }

    override suspend fun deleteAllUserBadge() {
        badgeDao.deleteAllUserBadge()
    }

    override suspend fun insertAllBadge(badge: List<Badge>) {
        badgeDao.insertAllBadge(*badge.map { it.toEntity() }.toTypedArray())
    }

    override suspend fun insertAllUserBadge(userBadge: List<UserBadge>) {
        badgeDao.insertAllUserBadge(*userBadge.map { it.toEntity() }.toTypedArray())
    }

    override suspend fun fetch(badge: List<Badge>) {
        badgeDao.deleteAllBadge()
        badgeDao.insertAllBadge(*badge.map { it.toEntity() }.toTypedArray())
    }

    override suspend fun fetch(userBadge: List<UserBadge>) {
        badgeDao.deleteAllUserBadge()
        badgeDao.insertAllUserBadge(*userBadge.map { it.toEntity() }.toTypedArray())
    }

}