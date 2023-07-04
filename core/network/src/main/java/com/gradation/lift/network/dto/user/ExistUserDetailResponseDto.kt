package com.gradation.lift.network.dto.user

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ExistUserDetailResponseDto(
    val result : Boolean
)
