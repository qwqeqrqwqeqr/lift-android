package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpKakaoUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<DataState<Boolean>> =
        authRepository.signUpKakao()
}