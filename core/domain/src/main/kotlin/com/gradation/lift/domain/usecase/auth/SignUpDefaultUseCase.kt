package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpDefaultUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(signUpInfo: DefaultSignUpInfo): Flow<DataState<Boolean>> =
        authRepository.signUpDefault(signUpInfo =signUpInfo)
}
