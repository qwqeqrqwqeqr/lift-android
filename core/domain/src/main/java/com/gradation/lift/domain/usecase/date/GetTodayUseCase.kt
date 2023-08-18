package com.gradation.lift.domain.usecase.date

import kotlinx.datetime.*
import javax.inject.Inject

class GetTodayUseCase @Inject constructor() {
    operator fun invoke(): LocalDate {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }
}