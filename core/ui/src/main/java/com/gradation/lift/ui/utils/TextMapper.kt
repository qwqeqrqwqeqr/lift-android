package com.gradation.lift.ui.utils

import androidx.core.util.rangeTo
import kotlinx.datetime.LocalTime

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


