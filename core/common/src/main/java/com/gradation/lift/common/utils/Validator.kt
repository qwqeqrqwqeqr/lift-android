package com.gradation.lift.common.utils

import android.util.Patterns
import java.util.regex.Pattern


val passwordPattern: Pattern = Pattern.compile("""^[a-zA-Z\\d`~!@#${'$'}%^&*()-_=+]{8,16}${'$'}""")
fun emailValidator(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
fun passwordValidator(password: String): Boolean = passwordPattern.matcher(password).matches()
fun passwordVerificationValidator(password: String, passwordVerification: String): Boolean =
    password == passwordVerification


fun lengthValidator(string: String, lowLimit: Int, highLimit: Int): Boolean =
    string.length in lowLimit..highLimit

data class Validator(
    val status: Boolean = false,
    val message: String = "",
)


