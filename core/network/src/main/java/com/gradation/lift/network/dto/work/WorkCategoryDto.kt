package com.gradation.lift.network.dto.work

import kotlinx.serialization.Serializable


@Serializable
data class WorkCategoryDto(
    val id: Int,
    val name: String,
    val workpart: WorkPartDto,
    val short_description: String? = null,
    val long_description: String? = null
)
