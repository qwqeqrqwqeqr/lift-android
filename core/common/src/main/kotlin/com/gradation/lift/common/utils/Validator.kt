package com.gradation.lift.common.utils

import android.util.Patterns
import java.util.regex.Pattern


val passwordPattern: Pattern = Pattern.compile("""^[a-zA-Z\\d`~!@#${'$'}%^&*()-_=+]{8,16}$""")
val namePattern: Pattern = Pattern.compile("""^[가-힣]{2,5}$""")


fun emailValidator(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
fun passwordValidator(password: String): Boolean = passwordPattern.matcher(password).matches()
fun passwordVerificationValidator(password: String, passwordVerification: String): Boolean =
    password == passwordVerification

fun nameValidator(name: String): Boolean = namePattern.matcher(name).matches()

fun lengthValidator(string: String, lowLimit: Int, highLimit: Int): Boolean =
    string.length in lowLimit..highLimit

fun weightValidator(weight: Float): Boolean = weight in 30f..200f
fun heightValidator(weight: Float): Boolean = weight in 100f..250f

fun routineSetNameValidator(name: String): Boolean =
    (name.length in 1..10)

fun routineSetDescriptionValidator(description: String): Boolean =
    (description.length in 0..20)

fun historyCommentValidator(name: String): Boolean =
    (name.length in 0..20)



data class Validator(
    val status: Boolean = false,
    val message: String = "",
)

