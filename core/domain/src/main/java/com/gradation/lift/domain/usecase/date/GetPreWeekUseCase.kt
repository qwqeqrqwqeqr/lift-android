package com.gradation.lift.domain.usecase.date

import kotlinx.datetime.*
import java.time.DayOfWeek
import javax.inject.Inject

/**
 * [GetPreWeekUseCase]
 * 지난주의 날짜 정보를 가져오는 유즈케이스
 * @since 2023-08-28 20:23:11
 */
class GetPreWeekUseCase @Inject constructor(){
    
    operator fun invoke(date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): List<LocalDate> {
        return when (date.dayOfWeek) {
            DayOfWeek.MONDAY -> (0..6).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.TUESDAY -> (-1..5).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.WEDNESDAY -> (-2..4).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.THURSDAY -> (-3..3).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.FRIDAY -> (-4..2).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.SATURDAY -> (-5..1).map { date.plus(DatePeriod(days = it)) }
            DayOfWeek.SUNDAY -> (-6..0).map { date.plus(DatePeriod(days = it)) }
        }.map { it.minus(DatePeriod(0, 0, 7)) }
    }
}

