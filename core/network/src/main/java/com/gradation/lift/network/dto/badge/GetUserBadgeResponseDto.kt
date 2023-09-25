package com.gradation.lift.network.dto.badge

import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.network.common.Constants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDateTime

@JsonClass(generateAdapter = true)
data class GetUserBadgeResponseDto(
    @Json(name = "user_badge")
    val userBadge: List<UserBadgeDto>
){
    fun toDomain(): List<UserBadge> = userBadge.map {
        UserBadge(
            badge = Badge(
                id = it.badge.id,
                name = it.badge.name,
                description = it.badge.description,
                hint = it.badge.hint,
                url = Constants.DEFAULT_S3_URL +it.badge.url
            ),
            badgeTimeStamp = LocalDateTime.parse(it.badgeTimeStamp),
            mainFlag = it.mainFlag
        )
    }
}
