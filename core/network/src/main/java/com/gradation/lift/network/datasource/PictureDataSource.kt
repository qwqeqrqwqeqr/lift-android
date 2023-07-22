package com.gradation.lift.network.datasource

import com.gradation.lift.model.picture.RoutineSetPicture
import com.gradation.lift.model.picture.UserProfilePicture
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface PictureDataSource {

    suspend fun getUserProfilePicture(): Flow<APIResult<List<UserProfilePicture>>>

    suspend fun getRoutineSetPicture(): Flow<APIResult<List<RoutineSetPicture>>>

}