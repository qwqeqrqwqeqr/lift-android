package com.gradation.lift.network.datasource

import com.gradation.lift.model.picture.RoutineSetPicture
import com.gradation.lift.model.picture.UserProfilePicture
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.PictureService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultPictureDataSource @Inject constructor(
    private val pictureService: PictureService,
    private val networkResultHandler: NetworkResultHandler,
) : PictureDataSource {
    override suspend fun getUserProfilePicture(): Flow<APIResult<List<UserProfilePicture>>> =flow {
        networkResultHandler {
            pictureService.getUserProfilePicture()
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))

                is APIResult.Success -> emit(APIResult.Success(result.data.toDomain()))
            }
        }
    }

    override suspend fun getRoutineSetPicture(): Flow<APIResult<List<RoutineSetPicture>>> =flow{
        networkResultHandler {
            pictureService.getRoutineSetPicture()
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))

                is APIResult.Success -> emit(APIResult.Success(result.data.toDomain()))
            }
        }
    }

}