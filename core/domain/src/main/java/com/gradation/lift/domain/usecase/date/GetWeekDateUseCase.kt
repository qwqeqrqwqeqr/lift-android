package com.gradation.lift.domain.usecase.date

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*
import java.time.DayOfWeek

class GetWeekDateUseCase {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): List<LocalDate> {
        return Clock.System.todayIn(TimeZone.currentSystemDefault()).let { date ->
            getCurrentWeekDate(date)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentWeekDate(date: LocalDate): List<LocalDate> =
    when (date.dayOfWeek) {
        DayOfWeek.MONDAY -> (0..6).map { date.plus(DatePeriod(days = it)) }
        DayOfWeek.TUESDAY -> (-1..5).map { date.plus(DatePeriod(days = it)) }
        DayOfWeek.WEDNESDAY -> (-2..4).map { date.plus(DatePeriod(days = it)) }
        DayOfWeek.THURSDAY -> (-3..3).map { date.plus(DatePeriod(days = it)) }
        DayOfWeek.FRIDAY -> (-4..2).map { date.plus(DatePeriod(days = it)) }
        DayOfWeek.SATURDAY -> (-5..1).map { date.plus(DatePeriod(days = it)) }
        DayOfWeek.SUNDAY -> (-6..0).map { date.plus(DatePeriod(days = it)) }
    }
