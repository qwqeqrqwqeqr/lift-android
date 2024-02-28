package com.gradation.lift.network.dto.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UpdateWorkCategoryFavoriteRequestDto(
    @SerialName("work_category_id")
    val workCategoryId: Int,


    )

