package com.gradation.lift.feature.ready_work.selection.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.model.common.toWeekday
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getIntValue
import com.gradation.lift.navigation.saved_state.setIntValue
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
    private val getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
) : ViewModel() {


    private val currentDate =
        MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))


    internal val selectedRoutineSetIdList = MutableStateFlow(emptyList<Int>())


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

    val routineSetRoutineSelection =
        selectedRoutineSetIdList.combine(currentDate) { routineSetIdList, currentDate ->
            getRoutineSetRoutineByWeekdayUseCase(currentDate.toWeekday()).map {
                when (it) {
                    is DataState.Fail -> RoutineSetRoutineSelectionUiState.Fail(message = it.message)
                    is DataState.Success -> {
                        if (it.data.isEmpty()) {
                            RoutineSetRoutineSelectionUiState.Empty
                        } else {
                            RoutineSetRoutineSelectionUiState.Success(
                                it.data.map { routineSetRoutine ->
                                    RoutineSetRoutineSelection(
                                        routineSetRoutine = routineSetRoutine,
                                        selected = (routineSetIdList.contains(routineSetRoutine.id))
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }.flatMapLatest { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineSetRoutineSelectionUiState.Loading
        )

    internal fun onClickWeekDayCard(): (LocalDate) -> Unit = {
        currentDate.value = it
    }

    fun updateRoutineSetIdList(): (Int, Boolean) -> Unit = { id, checked ->
        if (checked) {
            selectedRoutineSetIdList.update { it.plusElement(id) }
        } else {
            selectedRoutineSetIdList.update { it.minusElement(id) }
        }
        Log.d("list",selectedRoutineSetIdList.value.toString())
    }

    fun updatePreviousRoutineSetId(previousRoutineSetId :Int?) {
        previousRoutineSetId?.let { id ->
            selectedRoutineSetIdList.update { list ->
                list.plusElement(id)
            }
        }
    }
}