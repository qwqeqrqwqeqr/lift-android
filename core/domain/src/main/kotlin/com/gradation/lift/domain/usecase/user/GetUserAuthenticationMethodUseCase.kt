package com.gradation.lift.domain.usecase.user

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.model.auth.LoginMethod
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAuthenticationMethodUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<DataState<LoginMethod>> =
        userRepository.getUserAuthenticationMethod()
}