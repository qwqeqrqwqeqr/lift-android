package com.gradation.lift.oauth.kakao

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

/**
 * [KakaoOauthManager]
 * 카카오 OAuth를 통해 접근 가능한 기능들 정의
 * 반드시 signIn 을 성공한 이후에 사용할 수 있도록 구성할 것
 * @since 2023-08-16 11:12:02
 */
interface KakaoOauthManager {

    /**
     * [getUserId]
     * 카카오 사용자 아이디를 불러옵니다.
     * 실패 시, [DataState.Fail]에 오류 메시지를 담아 반환합니다.
     * @since 2023-08-16 11:15:48
     */
    fun getUserId(): Flow<DataState<String>>


    /**
     * [getUserEmail]
     * 카카오 사용자의 계정 이메일을 불러옵니다.
     * 실패 시, [DataState.Fail]에 오류 메시지를 담아 반환합니다.
     * @since 2023-08-16 15:57:58
     */
    fun getUserEmail(): Flow<DataState<String>>

    /**
     * [signOut]
     * 클라이언트 내에 토큰을 삭제하여 로그아웃을 진행합니다.
     * 실패 시, [DataState.Fail]에 오류메시지를 담아 반환합니다.
     * @since 2023-08-16 11:15:20
     */
    fun signOut(): Flow<DataState<Unit>>
}