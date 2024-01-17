package com.gradation.lift.network.dto.badge

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserBadgeDto(
    @SerialName("badge_id")
    val id: Int,
    @SerialName("badge_time_stamp")
    val badgeTimeStamp: String
)
