package com.gradation.lift.network.dto.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetWorkCategoryFavoriteResponseDto(
    @SerialName("work_category_id_list")
    val workCategoryIdList: List<Int>,


    )

