package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.PictureRepository
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.picture.PictureDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultPictureRepository @Inject constructor(
    private val pictureDataSource: PictureDataSource,
    private val dispatcherProvider: DispatcherProvider
) : PictureRepository {

    override fun getUserProfilePicture(): Flow<DataState<List<UserProfilePicture>>> = flow {
        pictureDataSource.getUserProfilePicture().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getRoutineSetPicture(): Flow<DataState<List<RoutineSetPicture>>> = flow {
        pictureDataSource.getRoutineSetPicture().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)
}