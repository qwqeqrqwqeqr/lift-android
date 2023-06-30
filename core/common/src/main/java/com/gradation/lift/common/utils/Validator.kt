package com.gradation.lift.common.utils

import android.util.Patterns
import java.util.regex.Pattern


val passwordPattern: Pattern = Pattern.compile("""^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$""")
fun emailValidator(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
fun passwordValidator(password: String): Boolean = passwordPattern.matcher(password).matches()
fun lengthValidator(string: String, lowLimit: Int, highLimit: Int): Boolean =
    string.length in lowLimit..highLimit

data class Validator(
    val status: Boolean = false,
    val message: String = "",
)


