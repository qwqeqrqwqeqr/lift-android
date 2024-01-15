package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.dto.user.UpdateUserDetailInfoRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailProfilePictureRequestDto
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


fun UserDetailInfo.toDto(): UpdateUserDetailInfoRequestDto =
    UpdateUserDetailInfoRequestDto(
        gender = this.gender.getGenderValue(),
        height = this.height,
        weight = this.weight,
        unitOfWeight = this.unitOfWeight.getUnitOfWeightValue()
    )

fun UserDetailProfilePicture.toDto(): UpdateUserDetailProfilePictureRequestDto =
    UpdateUserDetailProfilePictureRequestDto(
        profilePicture = this.profilePicture.replace(Constants.DEFAULT_S3_URL, ""),
    )
