package com.gradation.lift.network.dto.badge

import kotlinx.serialization.SerialName
SerialName(

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserBadgeMainFlagRequestDto(
    @SerialName("update_user_badge")
   val updateUserBadge : List<UpdateUserBadgeMainFlagDto>
)