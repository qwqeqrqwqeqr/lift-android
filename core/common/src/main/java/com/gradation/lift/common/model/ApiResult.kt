package com.gradation.lift.common.model

data class ApiResult<out T>(
    val status: Boolean,
    val message: String,
    val data: T?
)

