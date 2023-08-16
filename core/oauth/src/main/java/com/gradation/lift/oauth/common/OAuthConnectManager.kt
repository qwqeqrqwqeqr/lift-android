package com.gradation.lift.oauth.common

import com.gradation.lift.oauth.state.OAuthConnectState
import kotlinx.coroutines.flow.*

/**
 *  [OAuthConnectManager]
 * Sign In 기능 접근을 위해
 * 필요한 Activity Context 사용하기 위해
 * 개별적으로 기능 정의
 * @since 2023-08-16 11:01:58
 */
interface OAuthConnectManager  {


    /**
     *  [connectKakao]
     *  카카오 로그인
     *  [OAuthConnectState] 로 반환 값 생성
     *  실패시 실패 관련 메시지 전달
     */
    fun connectKakao(): Flow<OAuthConnectState>


    /**
     *  [connectNaver]
     *  네이버 로그인
     *  [OAuthConnectState] 로 반환 값 생성
     *  실패시 실패 관련 메시지 전달
     */
    fun connectNaver(): Flow<OAuthConnectState>

}

