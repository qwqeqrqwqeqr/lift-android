package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.user.Email
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckDuplicateEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(email: Email): Flow<DataState<Boolean>> =
        authRepository.checkDuplicateEmail(email = email)
}