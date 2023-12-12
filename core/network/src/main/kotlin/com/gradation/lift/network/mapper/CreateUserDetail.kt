package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.dto.user.CreateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UserDetailDto


fun UserDetail.toDto(): UserDetailDto =
    UserDetailDto(
        name = this.name,
        gender = this.gender.getGenderValue(),
        height = this.height,
        weight = this.weight,
        profilePicture = this.profilePicture.replace(
            Constants.DEFAULT_S3_URL,
            ""
        ),
        unitOfWeight = this.unitOfWeight.getUnitOfWeightValue()
    )
