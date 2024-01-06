package com.gradation.lift.network.dto.notification

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class NoticeDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("date")
    val date: String
)

