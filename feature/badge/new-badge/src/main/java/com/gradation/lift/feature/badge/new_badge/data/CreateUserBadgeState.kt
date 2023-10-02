package com.gradation.lift.feature.badge.new_badge.data

sealed interface CreateUserBadgeState {
    object Success: CreateUserBadgeState
    data class Fail(val message:String): CreateUserBadgeState
    object None: CreateUserBadgeState
}