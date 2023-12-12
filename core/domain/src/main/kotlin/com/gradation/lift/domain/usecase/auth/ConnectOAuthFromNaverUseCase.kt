package com.gradation.lift.domain.usecase.auth

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.OAuthConnectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectOAuthFromNaverUseCase @Inject constructor(
    private val oAuthConnectionRepository: OAuthConnectionRepository
) {
    operator fun invoke(): Flow<DataState<Unit>> =
        oAuthConnectionRepository.connectNaver()
}

