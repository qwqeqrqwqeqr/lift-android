package com.gradation.lift.ui.utils

import kotlinx.datetime.LocalTime
import kotlin.math.roundToLong

fun LocalTime.toText(): String {
    val second =
        if (second != 0 && hour == 0) "${second}초" else if (second == 0 && hour == 0) "0초" else ""
    val minute = if (minute != 0) "${minute}분 " else ""
    val hour = if (hour != 0) "${hour}시간 " else ""

    return hour + minute + second
}


fun Float.toText(): String {
    return if (
        toString()
            .slice(
                toString().indexOf('.') + 1 until toString().length
            )
            .none { it != '0' }
    ) this.toInt().toString()
    else
        this.toString()
}


fun Float.toWeightText(): String =
    ((this * 10.0).roundToLong() / 10.0).let { if ( it/1000 >= 1) 1000f else it.toFloat() }.toText()


fun Int.toRepetitionText(): Int = if(this in 1.. 50) this else 10


