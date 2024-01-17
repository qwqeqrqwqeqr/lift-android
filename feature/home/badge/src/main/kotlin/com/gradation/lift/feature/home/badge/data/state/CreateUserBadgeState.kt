package com.gradation.lift.feature.home.badge.data.state

sealed interface CreateUserBadgeState {
    data object Success: CreateUserBadgeState
    data class Fail(val message:String): CreateUserBadgeState
    data object None: CreateUserBadgeState
}