package com.gradation.lift.oauth.google

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

/**
 * [GoogleOauthManager]
 * 구글 OAuth를 통해 접근 가능한 기능들 정의
 * 반드시 signIn 을 성공한 이후에 사용할 수 있도록 구성할 것
 * @since 2023-12-26 18:40:53
 */
interface GoogleOauthManager {

    /**
     * [getUserId]
     * 구글 사용자 아이디를 불러옵니다.
     * 실패 시, [DataState.Fail]에 오류 메시지를 담아 반환합니다.
     * @since 2023-12-26 18:41:09
     */
    fun getUserId(): Flow<DataState<String>>


    /**
     * [getUserEmail]
     * 구글 사용자의 계정 이메일을 불러옵니다.
     * 실패 시, [DataState.Fail]에 오류 메시지를 담아 반환합니다.
     * @since 2023-12-26 18:41:09
     */
    fun getUserEmail(): Flow<DataState<String>>

    /**
     * [signOut]
     * 클라이언트 내에 토큰을 삭제하여 로그아웃을 진행합니다.
     * 실패 시, [DataState.Fail]에 오류메시지를 담아 반환합니다.
     * @since 2023-12-26 18:41:09
     */
    fun signOut(): Flow<DataState<Unit>>
}