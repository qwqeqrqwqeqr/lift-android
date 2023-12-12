package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.OAuthConnectionRepository
import com.gradation.lift.oauth.common.OAuthConnectionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultOAuthConnectionRepository @Inject constructor(
    private val oAuthConnectionManager: OAuthConnectionManager,
) : OAuthConnectionRepository {
    override fun connectKakao(): Flow<DataState<Unit>> = flow {
        oAuthConnectionManager.connectKakao().collect{
            emit(it)
        }
    }

    override fun connectNaver(): Flow<DataState<Unit>> = flow{
        oAuthConnectionManager.connectNaver().collect{
            emit(it)
        }
    }

}