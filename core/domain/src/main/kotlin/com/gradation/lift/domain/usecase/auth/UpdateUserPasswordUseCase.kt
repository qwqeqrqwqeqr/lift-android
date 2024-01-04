package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.UpdatePasswordInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(updatePasswordInfo: UpdatePasswordInfo): Flow<DataState<Boolean>> =
        authRepository.updateUserPassword(updatePasswordInfo)
}