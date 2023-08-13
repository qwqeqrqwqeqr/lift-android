package com.gradation.lift.model.model.auth


sealed class LoginMethod {
    data class Kakao(val value: String = "KAKAO") : LoginMethod()
    data class Naver(val value: String = "NAVER") : LoginMethod()
    data class Google(val value: String = "GOOGLE") : LoginMethod()
    data class Common(val value: String = "COMMON") : LoginMethod()
    data class None(val value: String = "") : LoginMethod()

    fun getValue(): String =
        when (this) {
            is Common -> this.value
            is Google -> this.value
            is Kakao -> this.value
            is Naver -> this.value
            is None -> this.value
        }

    companion object {
        const val KAKAO_VALUE = "KAKAO"
        const val NAVER_VALUE = "NAVER"
        const val GOOGLE_VALUE = "GOOGLE"
        const val COMMON_VALUE = "COMMON"
    }
}

fun String?.toLoginMethod(): LoginMethod =
    when (this) {
        LoginMethod.KAKAO_VALUE -> LoginMethod.Kakao()
        LoginMethod.NAVER_VALUE -> LoginMethod.Naver()
        LoginMethod.GOOGLE_VALUE -> LoginMethod.Google()
        LoginMethod.COMMON_VALUE -> LoginMethod.Common()
        else -> LoginMethod.None()
    }