package com.gradation.lift.network.dto.badge

import kotlinx.serialization.SerialName
SerialName(

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserBadgeRequestDto(
    @SerialName("user_badge")
    val createUserBadgeDto: CreateUserBadgeDto
)
