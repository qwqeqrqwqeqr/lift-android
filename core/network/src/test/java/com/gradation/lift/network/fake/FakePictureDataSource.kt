package com.gradation.lift.network.fake

import com.gradation.lift.model.picture.RoutineSetPicture
import com.gradation.lift.model.picture.UserProfilePicture
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.getRoutineSetPictureResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Picture.getUserProfilePictureResponseDto
import com.gradation.lift.network.datasource.PictureDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePictureDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    PictureDataSource {
    override suspend fun getUserProfilePicture(): Flow<APIResult<List<UserProfilePicture>>> =flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = getUserProfilePictureResponseDto.toUserProfilePicture()))
        }
    }

    override suspend fun getRoutineSetPicture(): Flow<APIResult<List<RoutineSetPicture>>> =flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = getRoutineSetPictureResponseDto.toRoutineSetPicture()))
        }
    }
}


