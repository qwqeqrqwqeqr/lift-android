package com.gradation.lift.network.datasource.picture

import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PictureDataSource {

    suspend fun getUserProfilePicture(): Flow<NetworkResult<List<UserProfilePicture>>>

    suspend fun getRoutineSetPicture(): Flow<NetworkResult<List<RoutineSetPicture>>>

}