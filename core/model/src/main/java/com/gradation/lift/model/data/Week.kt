package com.gradation.lift.model.data

enum class Week(val value: String) {
    Monday(value = "월요일"),
    Tuesday(value = "화요일"),
    Wednesday(value = "수요일"),
    Thursday(value = "목요일"),
    Friday(value = "금요일"),
    Saturday(value = "토요일"),
    Sunday(value = "일요일"),
}

fun String.toWeekType() = when {
    else -> Week.values().first { type -> type.value == this }
}