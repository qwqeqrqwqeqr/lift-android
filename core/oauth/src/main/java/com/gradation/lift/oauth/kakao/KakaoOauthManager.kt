package com.gradation.lift.oauth.kakao

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

interface KakaoOauthManager {

    fun getUserId() : Flow<DataState<String>>

    fun signOut(): Flow<DataState<Unit>>
}