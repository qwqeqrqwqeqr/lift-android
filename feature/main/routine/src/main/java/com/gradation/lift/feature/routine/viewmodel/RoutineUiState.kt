package com.gradation.lift.feature.routine.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

data class WeekDate(
    var day: String,
    val weekDay: String,
    var localDate: LocalDate?,
    var selected: Boolean,
)

data class WeekDateRoutine(
    val weekDateRoutine: List<com.gradation.lift.model.routine.RoutineSetRoutine>,
)


data class WeekDateUiState(
    val weekDate: List<WeekDate>
)

sealed interface WeekDateRoutineUiState {
    data class Success(val weekDateRoutine: WeekDateRoutine) : WeekDateRoutineUiState
    object Error : WeekDateRoutineUiState
    object Loading : WeekDateRoutineUiState
    object Empty: WeekDateRoutineUiState
}


@RequiresApi(Build.VERSION_CODES.O)
fun weekDateRoutineUiState(
    getRoutineSetRoutineByDateUseCase: Flow<DataState<List<com.gradation.lift.model.routine.RoutineSetRoutine>>>,
): Flow<WeekDateRoutineUiState> {

    return getRoutineSetRoutineByDateUseCase.map {
        when (it) {
            is DataState.Error -> WeekDateRoutineUiState.Error
            is DataState.Fail -> WeekDateRoutineUiState.Error
            is DataState.Loading -> WeekDateRoutineUiState.Loading
            is DataState.Success ->
            {
                if (it.data.isEmpty()) {
                    WeekDateRoutineUiState.Empty
                } else {

                    WeekDateRoutineUiState.Success(
                        weekDateRoutine = WeekDateRoutine(it.data)
                    )
                }
            }
        }
    }
}