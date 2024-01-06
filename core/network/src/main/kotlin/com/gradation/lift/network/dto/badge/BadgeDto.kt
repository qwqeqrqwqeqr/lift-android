package com.gradation.lift.network.dto.badge

import kotlinx.serialization.SerialName
SerialName(

import kotlinx.serialization.Serializable

@Serializable
data class BadgeDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("hint")
    val hint: String,
    @SerialName("url")
    val url: String,
    @SerialName("color")
    val color: String
)


