package com.gradation.lift.network.datasource.picture

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.PictureService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultPictureRemoteDataSource @Inject constructor(
    private val pictureService: PictureService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : PictureRemoteDataSource {
    override suspend fun getUserProfilePicture(): Flow<NetworkResult<List<UserProfilePicture>>> =
        flow {
            networkResultHandler {
                pictureService.getUserProfilePicture()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun getRoutineSetPicture(): Flow<NetworkResult<List<RoutineSetPicture>>> =
        flow {
            networkResultHandler {
                pictureService.getRoutineSetPicture()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }.flowOn(dispatcherProvider.default)

}