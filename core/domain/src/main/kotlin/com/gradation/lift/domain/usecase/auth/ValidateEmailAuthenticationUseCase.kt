package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.EmailAuthenticationValidationInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidateEmailAuthenticationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(emailAuthenticationValidationInfo: EmailAuthenticationValidationInfo): Flow<DataState<Boolean>> =
        authRepository.validateEmailAuthentication(emailAuthenticationValidationInfo)
}