package com.gradation.lift.feature.ready_work.selection.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.model.common.toWeekday
import com.gradation.lift.model.routine.RoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class ReadyWorkSelectionViewModel @Inject constructor(
    private val getWeekDateUseCase: GetWeekDateUseCase,
    private val getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase
) : ViewModel() {

    private val currentDate =
        MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))


    internal val selectedRoutineSet = MutableStateFlow(emptyList<RoutineSetRoutine>())

    internal val weekDate = currentDate.map {
        getWeekDateUseCase(it).map { localDate ->
            WeekdayCard(
                weekday = localDate.toWeekday(),
                localDate = localDate,
                selected = false
            ).apply {
                if (this.localDate == currentDate.value) this.selected = true
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    internal val weekDateRoutine: StateFlow<WeekDateRoutineUiState> =
        currentDate.flatMapLatest { currentDate ->
            getRoutineSetRoutineByWeekdayUseCase(currentDate.toWeekday()).map {
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
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WeekDateRoutineUiState.Loading
        )

    internal fun onClickWeekDayCard(): (LocalDate) -> Unit = {
        currentDate.value = it
    }
}