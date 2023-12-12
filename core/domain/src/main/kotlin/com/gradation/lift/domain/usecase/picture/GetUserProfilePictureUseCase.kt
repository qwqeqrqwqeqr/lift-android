package com.gradation.lift.domain.usecase.picture

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.PictureRepository
import com.gradation.lift.model.model.picture.UserProfilePicture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfilePictureUseCase @Inject constructor(
    private val pictureRepository: PictureRepository
){
    operator fun invoke(): Flow<DataState<List<UserProfilePicture>>> =
        pictureRepository.getUserProfilePicture()
}