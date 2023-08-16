package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.oauth.common.OAuthConnectManager
import com.gradation.lift.oauth.state.OAuthConnectState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectOAuthFromKakaoUseCase @Inject constructor(
    private val oAuthConnectManager: OAuthConnectManager,
) {
    operator fun invoke(): Flow<OAuthConnectState> =
        oAuthConnectManager.connectKakao()
}

