package com.gradation.lift.domain.usecase.user

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.model.user.UserDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userDetail: UserDetail): Flow<DataState<Unit>> =
        userRepository.updateUserDetail(userDetail)
}