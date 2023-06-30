package com.gradation.lift.common.utils

import android.util.Patterns


data class Validator(
    val status: Boolean = false,
    val message: String = "",
)

fun emailValidator(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()




