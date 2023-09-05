package com.gradation.lift.feature.history.analytics.util

fun Int.weekToText(): String = when (this) {
    1 -> "첫째"
    2 -> "둘째"
    3 -> "셋째"
    4 -> "넷째"
    5 -> "다섯째"
    6 -> "여섯째"
    else -> ""
}
