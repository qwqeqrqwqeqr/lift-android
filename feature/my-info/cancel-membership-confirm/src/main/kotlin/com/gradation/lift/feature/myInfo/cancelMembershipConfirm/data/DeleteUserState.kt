package com.gradation.lift.feature.myInfo.cancelMembershipConfirm.data

sealed interface DeleteUserState {

    data object Success : DeleteUserState
    data class Fail(val message: String) : DeleteUserState
    data object None : DeleteUserState
}