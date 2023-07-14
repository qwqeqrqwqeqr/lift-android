package com.gradation.lift.feature.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.time.DayOfWeek
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeekDateUseCase: GetWeekDateUseCase,
    private val getRoutineSetRoutineByDateUseCase: GetRoutineSetRoutineUseCase,
) : ViewModel() {

    internal val currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault())


    private val _weekDateUiState =
        MutableStateFlow(WeekDateUiState(getWeekDateUseCase().map { localDate ->
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
        }))
    var weekDateUiState = _weekDateUiState.asStateFlow()


    var weekDateRoutineUiState: StateFlow<WeekDateRoutineUiState> =
        weekDateRoutineUiState(
            getRoutineSetRoutineByDateUseCase = getRoutineSetRoutineByDateUseCase(),
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WeekDateRoutineUiState.Loading
        )


    fun onClickDate(selectedDate: LocalDate) {

        _weekDateUiState.update {
            it.copy(
                weekDate = it.weekDate.map { weekDate ->
                    WeekDate(
                        day = weekDate.day,
                        weekDay = weekDate.weekDay,
                        localDate = weekDate.localDate,
                        selected = weekDate.localDate == selectedDate
                    )
                }
            )
        }
        weekDateRoutineUiState = weekDateRoutineUiState(
            getRoutineSetRoutineByDateUseCase = getRoutineSetRoutineByDateUseCase(),
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WeekDateRoutineUiState.Loading
        )

    }
}




