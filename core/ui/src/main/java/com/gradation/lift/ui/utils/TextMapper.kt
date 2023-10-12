package com.gradation.lift.ui.utils

import android.icu.text.DecimalFormat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

/**
 * [toText]
 * LocalTime 데이터를 UI에서 적절하게 표시될 수 있게 변환해주는 메서드
 * @since 2023-08-28 22:35:44
 */
fun LocalTime.toText(): String {
    val second =
        if (second != 0 && hour == 0) "${second}초" else if (second == 0 && hour == 0) "0초" else ""
    val minute = if (minute != 0) "${minute}분 " else ""
    val hour = if (hour != 0) "${hour}시간 " else ""

    return hour + minute + second
}

/**
 * [toText]
 * Float 타입 데이터를 UI에서 적절하게 표시 될 수 있도록 변환해주는 메서드
 * @since 2023-10-11 22:51:48
 */
fun Float.toText(): String {
    return DecimalFormat("#.#").format(this)
}


/**
 * [toWeightText]
 * 무게를 UI에서 적절하게 표시될 수 있도록 변환해주는 함수
 * 무게를 입력할 때 사용할 것
 * @since 2023-08-28 22:37:40
 */
fun Float.toWeightText(): String = (if (this.toInt() in 1..2000) this else 10.0f).toText()


/**
 * [toRepetitionText]
 * 횟수를 UI에서 적절하게 표시될 수 있도록 변환햐주는 함수
 * 횟수를 입력할 때 사용할 것
 * @since 2023-08-28 22:38:09
 */
fun Int.toRepetitionText(): Int = if (this in 1..100) this else 10

/**
 * [toDayMonthText]
 * [LocalDate]를 월 일 형식으로 변환합니다.
 * @since 2023-09-05 20:59:43
 */
fun LocalDate.toDayMonthText(): String = "${this.monthNumber}월 ${this.dayOfMonth}일"
