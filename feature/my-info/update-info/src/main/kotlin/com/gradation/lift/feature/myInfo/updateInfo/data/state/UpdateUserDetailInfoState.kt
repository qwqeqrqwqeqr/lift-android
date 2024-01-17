package com.gradation.lift.feature.myInfo.updateInfo.data.state

/**
* [UpdateUserDetailInfoState]
* 사용자의 상세정보를 업데이트시 업데이트 상태를 관측하기위한 상태
* @since 2024-01-15 14:00:40
*/
sealed interface UpdateUserDetailInfoState {
    data object Success : UpdateUserDetailInfoState
    data class Fail(val message: String) : UpdateUserDetailInfoState
    data object None : UpdateUserDetailInfoState
}


