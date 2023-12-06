package com.gradation.lift.feature.my_info.update.data.state

/**
* [UpdateUserDetailState]
* 사용자의 상세정보를 업데이트시 업데이트 상태를 관측하기위한 상태
* @since 2023-08-26 13:09:11
*/
sealed interface UpdateUserDetailState {
    object Success : UpdateUserDetailState
    data class Fail(val message: String) : UpdateUserDetailState
    object None : UpdateUserDetailState
}


