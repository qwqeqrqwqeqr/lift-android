package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.auth.SignInInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInDefaultUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(signInInfo: SignInInfo): Flow<DataState<Boolean>> =
        authRepository.signInDefault(signInInfo =signInInfo)
}

