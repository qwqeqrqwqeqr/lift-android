package com.gradation.lift.database.datasource.user

import com.gradation.lift.database.dao.UserDao
import com.gradation.lift.database.mapper.toDomain
import com.gradation.lift.model.model.user.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultUserDataSource @Inject constructor(
    private val userDao: UserDao,
) : UserDataSource {
    override suspend fun getUser(): Flow<UserDetail> = flow {
        userDao.getAllUser().collect {
            emit(it.first().toDomain())
        }
    }

    override suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }

    override suspend fun insertUser(userDetail: UserDetail) {
        userDao.insertUser(userDetail.toDomain())
    }

}