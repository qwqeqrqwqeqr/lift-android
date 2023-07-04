package com.gradation.lift.network.dto.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateUserDetailResponseDto(
    val result: Boolean
)