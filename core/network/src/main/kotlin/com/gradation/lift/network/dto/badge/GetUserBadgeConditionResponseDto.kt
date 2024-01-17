package com.gradation.lift.network.dto.badge

import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.network.common.Constants
import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable

data class GetUserBadgeConditionResponseDto(
    @SerialName("badge")
    val badge: BadgeDto? = null
) {
    fun toDomain(): BadgeCondition =
        BadgeCondition(
            badge?.let {
                Badge(
                    id = it.id,
                    name = badge.name,
                    description = badge.description,
                    hint = badge.hint,
                    url = Constants.DEFAULT_S3_URL + badge.url,
                    color = it.color
                )
            }
        )
}
