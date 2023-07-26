package com.gradation.lift.database.model.user

import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.user.Gender


//TODO entity 수정
data class UserEntity(
    val name: String,
    val gender: Gender,
    val height: Float,
    val weight: Float,
    val profilePicture: String,
    val unitOfWeight: UnitOfWeight,
)
