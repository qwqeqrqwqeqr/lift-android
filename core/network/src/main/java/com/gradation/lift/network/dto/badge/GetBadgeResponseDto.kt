package com.gradation.lift.network.dto.badge

import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.network.common.Constants.DEFAULT_S3_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetBadgeResponseDto(
    @Json(name = "badge")
    val badge: List<BadgeDto>
) {
    fun toDomain(): List<Badge> = badge.map {
        Badge(
            id = it.id,
            name = it.name,
            description = it.description,
            hint = it.hint,
            url = DEFAULT_S3_URL+it.url
        )
    }
}
