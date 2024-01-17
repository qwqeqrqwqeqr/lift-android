package com.gradation.lift.network.dto.user

import com.gradation.lift.model.model.common.toUnitOfWeight
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.toGender
import com.gradation.lift.network.common.Constants.DEFAULT_S3_URL
import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class GetUserDetailResponseDto(
    @SerialName("user_detail")
    val userDetailDto: UserDetailDto,
) {
    fun toDomain() = UserDetail(
        name = userDetailDto.name,
        gender = userDetailDto.gender.toGender(),
        height = userDetailDto.height,
        weight = userDetailDto.weight,
        profilePicture = DEFAULT_S3_URL+userDetailDto.profilePicture,
        unitOfWeight = userDetailDto.unitOfWeight.toUnitOfWeight()
    )
}


