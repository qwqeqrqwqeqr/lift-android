package com.gradation.lift.model.user

data class UserDetail(
    val name: String,
    val gender: Gender,
    val height: Float,
    val weight: Float?,
    val unitOfWeight: UnitOfWeight,
)


