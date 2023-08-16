package com.gradation.lift.oauth.common

import com.gradation.lift.oauth.state.OAuthSignInState
import kotlinx.coroutines.flow.*

/**
 *  [OAuthSignInManager]
 * Sign In 기능 접근을 위해
 * 필요한 Activity Context 사용하기 위해
 * 개별적으로 기능 정의
 * @since 2023-08-16 11:01:58
 */
interface OAuthSignInManager  {


    /**
     *  [signInKakao]
     *  카카오 로그인
     *  [OAuthSignInState] 로 반환 값 생성
     *  실패시 실패 관련 메시지 전달
     */
    fun signInKakao(): Flow<OAuthSignInState>


    /**
     *  [signInNaver]
     *  네이버 로그인
     *  [OAuthSignInState] 로 반환 값 생성
     *  실패시 실패 관련 메시지 전달
     */
    fun signInNaver(): Flow<OAuthSignInState>

}

