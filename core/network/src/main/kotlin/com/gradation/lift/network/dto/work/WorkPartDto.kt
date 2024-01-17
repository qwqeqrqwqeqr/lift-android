package com.gradation.lift.network.dto.work

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class WorkPartDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)


