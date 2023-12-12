package com.gradation.lift.database.datasource.picture

import com.gradation.lift.database.dao.PictureDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultPictureDataSource @Inject constructor(
    private val pictureDao: PictureDao,
) : PictureDataSource {
    override suspend fun getAllUserProfilePicture(): Flow<List<UserProfilePicture>> = flow {
        pictureDao.getAllUserProfilePicture().collect { userProfilePictureEntity ->
            emit(userProfilePictureEntity.map { it.toDomain() })
        }
    }

    override suspend fun getAllRoutineSetPicture(): Flow<List<RoutineSetPicture>> = flow {
        pictureDao.getAllRoutineSetPicture().collect { rotuineSetProfilePicture ->
            emit(rotuineSetProfilePicture.map { it.toDomain() })
        }
    }

    override suspend fun deleteAllUserProfilePicture() {
        pictureDao.deleteAllUserProfilePicture()
    }

    override suspend fun deleteAllRoutineSetPicture() {
        pictureDao.deleteAllRoutineSetPicture()
    }

    override suspend fun insertAllUserProfilePicture(userProfilePicture: List<UserProfilePicture>) {
        pictureDao.insertAllUserProfilePicture(*userProfilePicture.map { it.toEntity() }
            .toTypedArray())
    }

    override suspend fun insertAllRoutineSetPicture(routineSetPicture: List<RoutineSetPicture>) {
        pictureDao.insertAllRoutineSetPicture(*routineSetPicture.map { it.toEntity() }
            .toTypedArray())
    }

    override suspend fun fetchUserProfilePicture(userProfilePicture: List<UserProfilePicture>) {
        pictureDao.deleteAllUserProfilePicture()
        pictureDao.insertAllUserProfilePicture(*userProfilePicture.map { it.toEntity() }
            .toTypedArray())
    }

    override suspend fun fetchRoutineSetPicture(routineSetPicture: List<RoutineSetPicture>) {
        pictureDao.deleteAllRoutineSetPicture()
        pictureDao.insertAllRoutineSetPicture(*routineSetPicture.map { it.toEntity() }
            .toTypedArray())
    }

}