package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.auth.SignUpInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(signUpInfo: SignUpInfo): Flow<DataState<Boolean>> =
        authRepository.signUp(signUpInfo =signUpInfo)
}
