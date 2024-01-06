package com.gradation.lift.network.dto.badge

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserBadgeMainFlagDto(
    @SerialName("badge_id")
    val id: Int,
    @SerialName("main_flag")
    val mainFlag: Boolean
)
