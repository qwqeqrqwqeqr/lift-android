package com.gradation.lift.oauth.naver

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

/**
 * [NaverOauthManager]
 * 네이버 OAuth를 통해 접근 가능한 기능들 정의
 * 반드시 signIn 을 성공한 이후에 사용할 수 있도록 구성할 것
 * @since 2023-08-16 11:12:12
 */
interface NaverOauthManager {

    /**
     * [getUserId]
     * 네이버 사용자 아이디를 불러옵니다.
     * 실패시 [DataState.Fail] 을 반환합니다.
     * @since 2023-08-16 11:14:31
     */
    fun getUserId() : Flow<DataState<String>>

    /**
     * [signOut]
     * 클라이언트 내에 토큰을 삭제하여 로그아웃을 진행합니다.
     * 약전계에서 로그아웃 호출 시
     * 저장된 토큰만 삭제하므로 약전계나
     * 네트워크 오류에 영향을 받지 않습니다.
     * @since 2023-08-16 11:14:37
     */
    fun signOut()
}