package com.gradation.lift.feature.myInfo.updateName.data.state

/**
* [UpdateNameState]
* 사용자의 상세정보를 업데이트시 업데이트 상태를 관측하기위한 상태
* @since 2024-01-15 14:00:40
*/
sealed interface UpdateNameState {
    data object Success : UpdateNameState
    data class Fail(val message: String) : UpdateNameState
    data object None : UpdateNameState
}


