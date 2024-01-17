package com.gradation.lift.domain.usecase.user

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.model.user.UserDetailName
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserDetailNameUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userDetailName: UserDetailName): Flow<DataState<Boolean>> =
        userRepository.updateUserDetailName(userDetailName)
}