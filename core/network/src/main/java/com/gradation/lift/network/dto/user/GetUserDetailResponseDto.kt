package com.gradation.lift.network.dto.user

import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.Gender.Companion.FEMALE_VALUE
import com.gradation.lift.model.user.Gender.Companion.MALE_VALUE
import com.gradation.lift.model.user.UnitOfWeight
import com.gradation.lift.model.user.UnitOfWeight.Companion.KG_VALUE
import com.gradation.lift.model.user.UnitOfWeight.Companion.LB_VALUE
import com.gradation.lift.model.user.UserDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetUserDetailResponseDto(
    @Json(name = "user_detail")
    val userDetailDto: UserDetailDto,
) {
    fun toUserDetail() = UserDetail(
        name = userDetailDto.name ?: "",
        gender = when (userDetailDto.gender) {
            MALE_VALUE -> Gender.MALE()
            FEMALE_VALUE -> Gender.FEMALE()
            else -> Gender.MALE()
        },
        height = userDetailDto.height ?: 0f,
        weight = userDetailDto.weight ?: 0f,
        unitOfWeight = when (userDetailDto.unitOfWeight) {
            KG_VALUE-> UnitOfWeight.KG()
            LB_VALUE-> UnitOfWeight.LB()
            else -> UnitOfWeight.KG()
        }
    )
}


