package com.gradation.lift.network.dto.notice

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
    @SerialName("category")
    val category: String,
    @SerialName("date")
    val date: String
)

