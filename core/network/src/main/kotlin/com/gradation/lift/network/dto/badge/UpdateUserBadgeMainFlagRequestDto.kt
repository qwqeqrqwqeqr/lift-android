package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateUserBadgeMainFlagRequestDto(
    @Json(name = "update_user_badge")
   val updateUserBadge : List<UpdateUserBadgeMainFlagDto>
)