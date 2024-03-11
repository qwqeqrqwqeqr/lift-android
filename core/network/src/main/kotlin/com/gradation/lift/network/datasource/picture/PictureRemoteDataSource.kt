package com.gradation.lift.network.datasource.picture

import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [PictureRemoteDataSource]
 * 서비스에서 사용되는 사진들에 대한 데이터 소스
 * @since 2023-08-28 22:08:33
 */
interface PictureRemoteDataSource {

    suspend fun getUserProfilePicture(): Flow<NetworkResult<List<UserProfilePicture>>>

    suspend fun getRoutineSetPicture(): Flow<NetworkResult<List<RoutineSetPicture>>>

}