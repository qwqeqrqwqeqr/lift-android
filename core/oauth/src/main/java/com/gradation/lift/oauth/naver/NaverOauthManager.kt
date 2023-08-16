package com.gradation.lift.oauth.naver

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Sign In 을 제외한
 * 나머지 기능 정의
 */
interface NaverOauthManager {

    fun getUserId() : Flow<DataState<String>>

    fun signOut()
}