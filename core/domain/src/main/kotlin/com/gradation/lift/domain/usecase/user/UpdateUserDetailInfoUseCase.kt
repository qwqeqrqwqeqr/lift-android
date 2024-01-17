package com.gradation.lift.domain.usecase.user

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.model.user.UserDetailInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserDetailInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userDetailInfo: UserDetailInfo): Flow<DataState<Boolean>> =
        userRepository.updateUserDetailInfo(userDetailInfo)
}