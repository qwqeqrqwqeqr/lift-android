package com.gradation.lift.network.dto.user

import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.Gender.Companion.FEMALE_VALUE
import com.gradation.lift.model.user.Gender.Companion.MALE_VALUE
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.common.UnitOfWeight.Companion.KG_VALUE
import com.gradation.lift.model.common.UnitOfWeight.Companion.LB_VALUE
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.Constants.DEFAULT_S3_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetUserDetailResponseDto(
    @Json(name = "user_detail")
    val userDetailDto: UserDetailDto,
) {
    fun toUserDetail() = UserDetail(
        name = userDetailDto.name,
        gender = when (userDetailDto.gender) {
            MALE_VALUE -> Gender.Male()
            FEMALE_VALUE -> Gender.Female()
            else -> Gender.Male()
        },
        height = userDetailDto.height,
        weight = userDetailDto.weight,
        profilePicture = DEFAULT_S3_URL+userDetailDto.profilePicture,
        unitOfWeight = when (userDetailDto.unitOfWeight) {
            KG_VALUE-> UnitOfWeight.Kg()
            LB_VALUE-> UnitOfWeight.Lb()
            else -> UnitOfWeight.Kg()
        }
    )
}


