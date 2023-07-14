package com.gradation.lift.feature.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.model.common.toWeekday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    getWeekDateUseCase: GetWeekDateUseCase,
    getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
) : ViewModel() {

    private val _currentDate =
        MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    var currentDate = _currentDate.asStateFlow()

    private val _weekDate =
        MutableStateFlow(getWeekDateUseCase(currentDate.value).map { localDate ->
            WeekDate(
                day = localDate.dayOfMonth.toString(),
                weekDay = localDate.toWeekday(),
                localDate = localDate,
                selected = false
            )
        }.apply {
            map { weekDate ->
                if (weekDate.localDate == currentDate.value) weekDate.selected = true
            }
        })
    var weekDate = _weekDate.asStateFlow()


    val weekDateRoutine: StateFlow<WeekDateRoutineUiState> =
        getRoutineSetRoutineByWeekdayUseCase(currentDate.value.toWeekday()).map {
            when (it) {
                is DataState.Fail -> WeekDateRoutineUiState.Fail(message = it.message)
                is DataState.Success -> {
                    if (it.data.isEmpty()) {
                        WeekDateRoutineUiState.Empty
                    } else {

                        WeekDateRoutineUiState.Success(
                            weekDateRoutine = it.data
                        )
                    }
                }
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = WeekDateRoutineUiState.Loading
            )


    fun onClickDate(selectedDate: LocalDate) {
        _currentDate.value = selectedDate
    }
}




