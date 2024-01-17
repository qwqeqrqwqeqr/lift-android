package com.gradation.lift.feature.myInfo.profile.data.state



/**
 * [SignOutState]
 * 사용자의 로그아웃 상태 해당 상태가 [Success] 일 경우 로그아웃 된다
 * @since 2023-09-01 14:07:35
 */
interface SignOutState {

    object Success : SignOutState
    data class Fail(val message: String) : SignOutState
    object None : SignOutState
}
