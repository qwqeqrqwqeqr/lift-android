package com.gradation.lift.domain.usecase.date

import kotlinx.datetime.*
import javax.inject.Inject

/**
 * [GetTodayUseCase]
 * 오늘 날짜를 가져오는 유즈케이스
 * @since 2023-08-28 22:02:33
 */
class GetTodayUseCase @Inject constructor() {
    operator fun invoke(): LocalDate {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }
}