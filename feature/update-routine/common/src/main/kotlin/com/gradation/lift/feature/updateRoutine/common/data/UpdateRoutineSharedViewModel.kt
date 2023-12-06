package com.gradation.lift.feature.updateRoutine.common.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UpdateRoutineSharedViewModel @Inject constructor(
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
) : ViewModel() {


    private val _currentRoutineSetRoutine: MutableStateFlow<RoutineSetRoutine> =
        MutableStateFlow(RoutineSetRoutine())
    val currentRoutineSetRoutine: StateFlow<RoutineSetRoutine> =
        _currentRoutineSetRoutine.asStateFlow()

    private var routineSetId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val setRoutineSetId: (Int?) -> Unit = { routineSetId.value = it }

    val routineSetRoutine: StateFlow<RoutineUiState> =
        routineSetId.flatMapLatest {
            getRoutineSetRoutineByRoutineSetIdUseCase(
                it?.let { setOf(it) } ?: emptySet()
            )
        }.map { routineSetRoutine ->
            when (routineSetRoutine) {
                is DataState.Fail -> RoutineUiState.Fail("루틴이 존재하지 않습니다.")
                is DataState.Success -> with(routineSetRoutine.data) {
                    if (this.isEmpty()) RoutineUiState.Fail("루틴이 존재하지 않습니다.")
                    else {
                        _currentRoutineSetRoutine.update { this.first() }
                        RoutineUiState.Success(this.first())
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = RoutineUiState.Loading
        )
}

