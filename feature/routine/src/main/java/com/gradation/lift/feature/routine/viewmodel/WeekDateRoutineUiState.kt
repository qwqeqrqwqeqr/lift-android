package com.gradation.lift.feature.routine.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.RoutineSetRoutine
import com.gradation.lift.domain.usecase.date.GetCurrentDateUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByDateUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import java.time.DayOfWeek


sealed interface WeekDateRoutineUiState {
    data class Success(val weekDateRoutine: WeekDateRoutine) : WeekDateRoutineUiState
    object Error : WeekDateRoutineUiState
    object Loading : WeekDateRoutineUiState
    object Empty : WeekDateRoutineUiState
}


data class WeekDateRoutine(
    val weekDateRoutine: List<RoutineSetRoutine>,
    val weekDate: List<WeekDate>
)


data class WeekDate(
    val day: String,
    val weekDay: String,
    val localDate: LocalDate,
    var selected: Boolean
)

@RequiresApi(Build.VERSION_CODES.O)
fun weekDateRoutineUiState(
    getRoutineSetRoutineByDateUseCase: GetRoutineSetRoutineByDateUseCase,
    getCurrentDateUseCase: GetCurrentDateUseCase,
    getWeekDateUseCase: GetWeekDateUseCase
): Flow<WeekDateRoutineUiState> {
    val currentDate = getCurrentDateUseCase()
    val weekDate: List<WeekDate> = getWeekDateUseCase().map { localDate ->
        WeekDate(
            day = localDate.dayOfMonth.toString(),
            weekDay = when (localDate.dayOfWeek) {
                DayOfWeek.MONDAY -> "월"
                DayOfWeek.TUESDAY -> "화"
                DayOfWeek.WEDNESDAY -> "수"
                DayOfWeek.THURSDAY -> "목"
                DayOfWeek.FRIDAY -> "금"
                DayOfWeek.SATURDAY -> "토"
                DayOfWeek.SUNDAY -> "일"
            },
            localDate = localDate,
            selected = false
        )
    }.also {
        it.map { weekDate ->
            if (weekDate.localDate == currentDate) weekDate.selected = true
        }
    }
    return getRoutineSetRoutineByDateUseCase(currentDate).map {
        when (it) {
            is DataState.Error -> WeekDateRoutineUiState.Error
            is DataState.Fail -> WeekDateRoutineUiState.Error
            is DataState.Loading -> WeekDateRoutineUiState.Loading
            is DataState.Success ->
                if (it.data.isEmpty()) {
                    WeekDateRoutineUiState.Empty
                } else {
                    WeekDateRoutineUiState.Success(
                        WeekDateRoutine(
                            weekDateRoutine = it.data,
                            weekDate = weekDate
                        )
                    )
                }
        }
    }
}