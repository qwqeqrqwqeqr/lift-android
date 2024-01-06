package com.gradation.lift.network.dto.user

import kotlinx.serialization.SerialName
SerialName(

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDetailRequestDto(
    @SerialName("user_detail")
    val userDetailDto: UserDetailDto
)
