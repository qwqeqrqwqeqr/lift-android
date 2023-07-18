package com.gradation.lift.feature.ready_work.selection.data

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.user.UserDetail
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

internal data class WeekdayCard(
    val weekday: Weekday = Weekday.None(),
    val localDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    var selected: Boolean = false,
)

data class RoutineSetRoutineSelection(
    val routineSetRoutine: RoutineSetRoutine,
    val selected: Boolean,
)


sealed interface RoutineSetRoutineSelectionUiState {
    data class Success(val routineSetRoutineSelection: List<RoutineSetRoutineSelection>) :
        RoutineSetRoutineSelectionUiState
    data class Fail(val message: String) : RoutineSetRoutineSelectionUiState
    object Loading : RoutineSetRoutineSelectionUiState
    object Empty : RoutineSetRoutineSelectionUiState
}



