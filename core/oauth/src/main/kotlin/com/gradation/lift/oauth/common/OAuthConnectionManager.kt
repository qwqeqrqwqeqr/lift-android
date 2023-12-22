package com.gradation.lift.oauth.common

import android.content.Intent
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
    fun connectNaver(): Flow<DataState<Unit>>


    /**
     *  [getGoogleClientIntent]
     *  구글 인텐트 불러오기
     */
    fun getGoogleClientIntent(): Flow<Intent>

}

