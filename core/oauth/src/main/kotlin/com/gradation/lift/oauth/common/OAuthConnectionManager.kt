package com.gradation.lift.oauth.common

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.*

/**
 *  [OAuthConnectionManager]
 * Sign In 기능 접근을 위해
 * 필요한 Activity Context 사용하기 위해
 * 개별적으로 기능 정의
 * @since 2023-08-16 11:01:58
 */
interface OAuthConnectionManager  {


    /**
     *  [connectKakao]
     *  카카오 로그인
     *  실패시 실패 관련 메시지 전달
     */
    fun connectKakao(): Flow<DataState<Unit>>


    /**
     *  [connectNaver]
     *  네이버 로그인
     *  실패시 실패 관련 메시지 전달
     */
    fun connectNaver(context:Context): Flow<DataState<Unit>>


    /**
     *  [getGoogleClient]
     *  구글 클라이언트 불러오기
     */
    fun getGoogleClient(): GoogleSignInClient

}

