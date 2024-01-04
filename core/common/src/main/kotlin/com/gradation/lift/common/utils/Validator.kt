package com.gradation.lift.common.utils

import android.util.Patterns
import java.util.regex.Pattern


val passwordPattern: Pattern = Pattern.compile("""^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@${'$'}%^&*-]).{2,}$""")
val namePattern: Pattern = Pattern.compile("""^[가-힣]{2,8}$""")
val decimalNumberPattern :Pattern = Pattern.compile("""^\d+(\.\d+)?${'$'}""")
fun emailValidator(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
fun passwordValidator(password: String): Boolean = passwordPattern.matcher(password).matches()
fun passwordVerificationValidator(password: String, passwordVerification: String): Boolean =
    password == passwordVerification

fun nameValidator(name: String): Boolean = namePattern.matcher(name).matches()

fun lengthValidator(string: String, lowLimit: Int, highLimit: Int): Boolean =
    string.length in lowLimit..highLimit

fun decimalNumberValidator(number:String) : Boolean = decimalNumberPattern.matcher(number).matches()

fun weightValidator(weight: Float): Boolean = weight > 0f
fun heightValidator(height: Float): Boolean = height > 0f

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


