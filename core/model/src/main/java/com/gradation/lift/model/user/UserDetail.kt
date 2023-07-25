package com.gradation.lift.model.user

import com.gradation.lift.model.common.UnitOfWeight

data class UserDetail(
    val name: String,
    val gender: Gender,
    val height: Float,
    val weight: Float,
    val profilePicture: String,
    val unitOfWeight: UnitOfWeight,
)


