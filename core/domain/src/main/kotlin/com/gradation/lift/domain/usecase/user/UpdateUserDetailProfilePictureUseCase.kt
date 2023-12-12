package com.gradation.lift.domain.usecase.user

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserDetailProfilePictureUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(updateUserDetailProfilePicture: UserDetailProfilePicture): Flow<DataState<Unit>> =
        userRepository.updateUserDetailProfilePicture(updateUserDetailProfilePicture)
}