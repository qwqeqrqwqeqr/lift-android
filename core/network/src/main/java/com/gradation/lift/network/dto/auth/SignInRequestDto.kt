package com.gradation.lift.network.dto.auth

import com.gradation.lift.network.dto.routine.RoutineSetDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignInRequestDto(
    @Json(name = "id")
    val id : String,
    @Json(name = "authentication_method")
    val authenticationMethod : String= "COMMON",
    @Json(name = "password")
    val password : String,
)
