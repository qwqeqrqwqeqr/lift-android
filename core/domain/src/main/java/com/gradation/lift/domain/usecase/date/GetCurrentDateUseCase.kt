package com.gradation.lift.domain.usecase.date

import kotlinx.datetime.*

class GetCurrentDateUseCase {
    operator fun invoke(): LocalDate {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }
}