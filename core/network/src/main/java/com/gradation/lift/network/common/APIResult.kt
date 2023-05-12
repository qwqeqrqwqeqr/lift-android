package com.gradation.lift.network.common

data class APIResult<out T>(
    val status: Boolean,
    val message: String,
    val data: T?
)

