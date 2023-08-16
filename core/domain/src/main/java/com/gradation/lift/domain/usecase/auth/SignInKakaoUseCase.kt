package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.oauth.common.OAuthSignInManager
import com.gradation.lift.oauth.state.OAuthSignInState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInKakaoUseCase @Inject constructor(
    private val oAuthSignInManager: OAuthSignInManager) {
    operator fun invoke(): Flow<OAuthSignInState> =
        oAuthSignInManager.signInKakao()
}

