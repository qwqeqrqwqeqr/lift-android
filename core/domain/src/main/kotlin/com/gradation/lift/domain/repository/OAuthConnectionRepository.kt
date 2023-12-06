package com.gradation.lift.domain.repository

import kotlinx.coroutines.flow.Flow
import com.gradation.lift.common.model.DataState

/**
 *  [OAuthConnectionRepository]
 * OAuth Sign In 기능 접근을 위해 필요한
 * Activity Context 사용하기 위해 개별적으로 기능을 정의 하였음
 * @since 2023-08-16 11:01:58
 */
interface OAuthConnectionRepository {


    /**
     *  [connectKakao]
     *  카카오 oauth 연결
     *  실패시 실패 관련 메시지 전달
     *  @since 2023-08-28 18:27:47
     */
    fun connectKakao(): Flow<DataState<Unit>>


    /**
     *  [connectNaver]
     *  네이버 oauth 연결
     *  실패시 실패 관련 메시지 전달
     *  @since 2023-08-28 18:27:53
     */
    fun connectNaver(): Flow<DataState<Unit>>


}