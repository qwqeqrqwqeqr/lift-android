package com.gradation.lift.network.dto.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UpdateWorkCategoryFavoriteResponseDto(
    @SerialName("result")
    val result: Boolean,


    )

