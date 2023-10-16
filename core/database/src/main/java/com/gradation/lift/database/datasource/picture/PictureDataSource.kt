package com.gradation.lift.database.datasource.picture

import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import kotlinx.coroutines.flow.Flow

/**
 * [getAllUserProfilePicture] 사용자가 사용할 수 있는 프로필 사진 조회
 * [getAllRoutineSetPicture] 모든 루틴세트에 사용할 수 있는 프로필 사진 조회
 * [deleteAllUserProfilePicture] 모든 프로필 사진 삭제
 * [deleteAllRoutineSetPicture] 모든 프로필 사진 삭제
 * [insertAllUserProfilePicture] 프로필 사진 추가
 * [insertAllRoutineSetPicture] 프로필 사진 추가
 */
interface PictureDataSource {

    suspend fun getAllUserProfilePicture(): Flow<List<UserProfilePicture>>
    suspend fun getAllRoutineSetPicture(): Flow<List<RoutineSetPicture>>
    suspend fun deleteAllUserProfilePicture()
    suspend fun deleteAllRoutineSetPicture()
    suspend fun insertAllUserProfilePicture(userProfilePicture: List<UserProfilePicture>)
    suspend fun insertAllRoutineSetPicture(routineSetPicture: List<RoutineSetPicture>)

    suspend fun fetch(userProfilePicture: List<UserProfilePicture>)
    suspend fun fetch(routineSetPicture: List<RoutineSetPicture>)
}