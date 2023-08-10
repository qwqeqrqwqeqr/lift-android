package com.gradation.lift.feature.home.data

import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.user.UserDetail
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

internal data class WeekDate(
    var day: String = "",
    val weekday: Weekday = Weekday.None(),
    var localDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    var selected: Boolean = false,
)


sealed interface WeekDateRoutineUiState {
    data class Success(val weekDateRoutine: List<RoutineSetRoutine>) : WeekDateRoutineUiState
    data class Fail(val message: String) : WeekDateRoutineUiState
    object Loading : WeekDateRoutineUiState
    object Empty: WeekDateRoutineUiState
}



sealed interface UserDetailUiState {
    data class Success(val userDetail: UserDetail) : UserDetailUiState
    data class Fail(val message: String) : UserDetailUiState
    object Loading : UserDetailUiState
}