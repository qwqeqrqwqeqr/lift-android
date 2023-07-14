package com.gradation.lift.feature.home.data

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.RoutineSetRoutine
import kotlinx.datetime.LocalDate

data class WeekDate(
    var day: String,
    val weekDay: Weekday,
    var localDate: LocalDate?,
    var selected: Boolean,
)




sealed interface WeekDateRoutineUiState {
    data class Success(val weekDateRoutine: List<RoutineSetRoutine>) : WeekDateRoutineUiState
    data class Fail(val message: String) : WeekDateRoutineUiState
    object Loading : WeekDateRoutineUiState
    object Empty: WeekDateRoutineUiState
}


