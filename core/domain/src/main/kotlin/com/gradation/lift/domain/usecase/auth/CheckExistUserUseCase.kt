package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckExistUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(userId: String): Flow<DataState<Boolean>> =
        authRepository.checkExistUser(userId)
}