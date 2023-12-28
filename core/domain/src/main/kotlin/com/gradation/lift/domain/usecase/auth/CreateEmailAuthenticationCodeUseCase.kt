package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.EmailAuthenticationInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateEmailAuthenticationCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(emailAuthenticationInfo: EmailAuthenticationInfo): Flow<DataState<Boolean>> =
        authRepository.createEmailAuthenticationCode(emailAuthenticationInfo)
}