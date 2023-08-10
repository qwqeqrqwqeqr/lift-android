package com.gradation.lift.feature.work.routine_selection.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.feature.work.work.data.model.RoutineSelection
import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection
import com.gradation.lift.model.model.common.toWeekday
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class WorkRoutineSelectionViewModel @Inject constructor(
    private val getWeekDateUseCase: GetWeekDateUseCase,
    private val getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
    private val getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
) : ViewModel() {


    private val currentDate =
        MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))


    val selectedRoutineSetList = MutableStateFlow(emptyList<RoutineSetRoutine>())
    private val openedRoutineIdList = MutableStateFlow(emptySet<Int>())

    internal val selectedRoutineCount = selectedRoutineSetList.map { it -> it.size }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

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
        combine(
            openedRoutineIdList,
            selectedRoutineSetList,
            currentDate
        ) { openedRoutineIdList, selectedRoutineSetList, currentDate ->
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
                                        id = routineSetRoutine.id,
                                        name = routineSetRoutine.name,
                                        description = routineSetRoutine.description,
                                        weekday = routineSetRoutine.weekday,
                                        selected = (selectedRoutineSetList.map { it -> it.id }
                                            .contains(routineSetRoutine.id)),
                                        routine = routineSetRoutine.routine.map { routine ->
                                            RoutineSelection(
                                                routine = routine,
                                                opened = (openedRoutineIdList.contains(routine.id))
                                            )
                                        }
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

    internal fun updateCurrentDate(): (LocalDate) -> Unit = {
        currentDate.value = it
    }

    fun updateSelectedRoutineSetIdList(): (RoutineSetRoutine, Boolean) -> Unit =
        { routineSetRoutine, checked ->
            if (checked) {
                selectedRoutineSetList.update { it.plusElement(routineSetRoutine) }
            } else {
                selectedRoutineSetList.update { it.minusElement(routineSetRoutine) }
            }
        }

    fun updateOpenedRoutineIdList(): (Int, Boolean) -> Unit = { id, checked ->
        if (checked) {
            openedRoutineIdList.update { it.plusElement(id) }
        } else {
            openedRoutineIdList.update { it.minusElement(id) }
        }
    }


    fun updateSelectedRoutineSetId(selectedRoutineSetRoutineId: Int?) {
        selectedRoutineSetRoutineId?.let { id ->
            viewModelScope.launch {
                getRoutineSetRoutineByRoutineSetIdUseCase(setOf(id)).collect {
                    when (it) {
                        is DataState.Fail -> {}
                        is DataState.Success -> {
                            selectedRoutineSetList.update { list ->
                                Log.d("lift", it.data.toString())
                                list.plus(it.data)
                            }
                        }
                    }
                }
            }
        }
    }


}