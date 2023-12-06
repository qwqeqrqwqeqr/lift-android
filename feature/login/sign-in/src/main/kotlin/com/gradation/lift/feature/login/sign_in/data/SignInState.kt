package com.gradation.lift.feature.login.sign_in.data


/**
 * [SignInState]
 * 로그인 성공 실패 여부를 표시하는 상태
 * 기본값은 [None]으로 설정되어 있음
 * @since 2023-08-17 12:00:10
 */
sealed interface SignInState {

    /**
     * [Success]
     * 로그인 성공 상태
     * @param existUserDetail
     * 사용자의 상세정보가 존재하는지에 대한 여부
     * 존재하지 않을 경우 상제정보 입력페이지로 이동함
     * @since 2023-08-17 12:00:18
     */
    data class Success(val existUserDetail: Boolean) : SignInState
    data class Fail(val message: String) : SignInState
    object None : SignInState
}