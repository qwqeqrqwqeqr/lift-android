package com.gradation.lift.network.fake

import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.getRoutineSetPictureResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.getUserProfilePictureResponseDto
import com.gradation.lift.network.datasource.picture.PictureDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePictureDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    PictureDataSource {
    override suspend fun getUserProfilePicture(): Flow<NetworkResult<List<UserProfilePicture>>> =flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = getUserProfilePictureResponseDto.toDomain()))
        }
    }

    override suspend fun getRoutineSetPicture(): Flow<NetworkResult<List<RoutineSetPicture>>> =flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = getRoutineSetPictureResponseDto.toDomain()))
        }
    }
}


