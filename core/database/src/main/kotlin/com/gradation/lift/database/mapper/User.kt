package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.user.UserEntity
import com.gradation.lift.model.model.user.UserDetail

fun UserDetail.toDomain() = UserEntity(
    name, gender, height, weight, profilePicture, unitOfWeight
)