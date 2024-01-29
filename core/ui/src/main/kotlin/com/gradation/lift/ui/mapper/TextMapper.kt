package com.gradation.lift.ui.mapper

import android.icu.text.DecimalFormat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

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
 * [toDayMonthText]
 * [LocalDate]를 월 일 형식으로 변환합니다.
 * @since 2023-09-05 20:59:43
 */
fun LocalDate.toDayMonthText(): String = "${this.monthNumber}월 ${this.dayOfMonth}일"


/**
 * [toDateText]
 * "00.00 형식으로 날짜 출력형식을 변경합니다."
 * @since 2024-01-23 13:22:14
 */
fun LocalDate.toDateText(): String =
    "${year.toString().subSequence(2, 4)}.${DecimalFormat("00").format(monthNumber)}.${dayOfMonth}"
/**
 * [toTimerText]
 * "00:00:00 형식으로 시간 출력형식을 변경합니다."
 * @since 2024-01-23 13:22:14
 */
fun LocalTime.toTimerText(): String =
    with(DateTimeFormatter.ofPattern("HH:mm:ss")) {
        toJavaLocalTime().format(this)
    }

/**
 * [toDayMonthYearText]
 * "0000.00.00 형식으로 날짜 출력형식을 변경합니다."
 * @since 2024-01-23 13:22:14
 */
fun LocalDateTime.toDayMonthYearText(): String =
    toJavaLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

/**
 * [toDayMonthYearText]
 * "0000.00 형식으로 날짜 출력형식을 변경합니다."
 * @since 2024-01-29 14:42:04
 */
fun LocalDate.toMonthYearText(): String =
    toJavaLocalDate().format(DateTimeFormatter.ofPattern("yyyy.MM"))