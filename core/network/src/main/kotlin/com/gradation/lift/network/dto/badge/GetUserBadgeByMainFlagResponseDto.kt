package com.gradation.lift.network.dto.badge

import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.network.common.Constants
import kotlinx.datetime.LocalDateTime.Companion.parse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetUserBadgeByMainFlagResponseDto(
    @SerialName("user_badge")
    val userBadge: List<UserBadgeDto>
) {
    fun toDomain(): List<UserBadge> = userBadge.map {
        UserBadge(
            badge = Badge(
                id = it.badge.id,
                name = it.badge.name,
                description = it.badge.description,
                hint = it.badge.hint,
                url = Constants.DEFAULT_S3_URL + it.badge.url,
                color = it.badge.color,
                backgroundColor = it.badge.backgroundColor
            ),
            badgeTimeStamp = parse(it.badgeTimeStamp),
            mainFlag = it.mainFlag
        )
    }
}
