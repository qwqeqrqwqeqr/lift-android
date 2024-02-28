package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.Picture.ROUTINE_SET_PICTURE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Picture.USER_PROFILE_PICTURE_DTO
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.picture.PictureDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePictureDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    PictureDataSource {
    override suspend fun getUserProfilePicture(): Flow<NetworkResult<List<UserProfilePicture>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(
                    NetworkResult.Success(
                        data = listOf(
                            USER_PROFILE_PICTURE_DTO.toDomain()
                        )
                    )
                )
            }
        }

    override suspend fun getRoutineSetPicture(): Flow<NetworkResult<List<RoutineSetPicture>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(
                    NetworkResult.Success(
                        data = listOf(
                            ROUTINE_SET_PICTURE_DTO.toDomain()
                        )
                    )
                )
            }
        }
}


