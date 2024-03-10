package com.gradation.lift.database.datasource.userBadge

import com.gradation.lift.database.dao.UserBadgeDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultUserBadgeLocalDataSource @Inject constructor(
    private val userBadgeDao: UserBadgeDao,
) : UserBadgeLocalDataSource {

    override fun getAllUserBadge(): Flow<List<UserBadge>> = flow {
        userBadgeDao.getAllUserBadge().collect { userBadgeEntityList ->
            emit(userBadgeEntityList.map { it.toDomain() })
        }
    }

    override suspend fun fetch(userBadge: List<UserBadge>) {
        userBadgeDao.deleteAllUserBadge()
        userBadgeDao.insertAllUserBadge(*userBadge.map { it.toEntity() }.toTypedArray())
    }

    override suspend fun updateUserBadgeMainFlag(updateUserBadgeMainFlag: UpdateUserBadgeMainFlag) {
        updateUserBadgeMainFlag.updateUserBadgeMainFlag.forEach {
            userBadgeDao.updateUserBadgeMainFlag(
                badgeId = it.first,
                mainFlag = it.second
            )
        }
    }
}