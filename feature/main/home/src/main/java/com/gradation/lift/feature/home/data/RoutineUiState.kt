package com.gradation.lift.feature.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

data class WeekDate(
    var day: String,
    val weekDay: String,
    var localDate: LocalDate?,
    var selected: Boolean,
)

data class WeekDateRoutine(
    val weekDateRoutine: List<RoutineSetRoutine>,
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
    getRoutineSetRoutineByDateUseCase: Flow<DataState<List<RoutineSetRoutine>>>,
): Flow<WeekDateRoutineUiState> {

    return getRoutineSetRoutineByDateUseCase.map {
        when (it) {
            is DataState.Fail -> WeekDateRoutineUiState.Error
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