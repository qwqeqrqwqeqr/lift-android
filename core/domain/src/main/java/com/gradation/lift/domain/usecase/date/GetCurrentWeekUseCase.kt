package com.gradation.lift.domain.usecase.date

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*
import java.time.DayOfWeek
import javax.inject.Inject

/**
 * [GetCurrentWeekUseCase]
 * 해당 일이 속해있는 이번 주의 날짜 정보를 가져오는 유즈케이스
 * @since 2023-08-28 20:23:11
 */
class GetCurrentWeekUseCase @Inject constructor(){
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): List<LocalDate> {
        return when (date.dayOfWeek) {
            DayOfWeek.MONDAY -> (0..6).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.TUESDAY -> (-1..5).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.WEDNESDAY -> (-2..4).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.THURSDAY -> (-3..3).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.FRIDAY -> (-4..2).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.SATURDAY -> (-5..1).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.SUNDAY -> (-6..0).map { date.plus(DatePeriod(days = it)) }
        }
    }
}

