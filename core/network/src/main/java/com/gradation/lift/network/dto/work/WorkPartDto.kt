package com.gradation.lift.network.dto.work

import kotlinx.serialization.Serializable


@Serializable
data class WorkPartDto(
    val id: Int,
    val name: String
)
