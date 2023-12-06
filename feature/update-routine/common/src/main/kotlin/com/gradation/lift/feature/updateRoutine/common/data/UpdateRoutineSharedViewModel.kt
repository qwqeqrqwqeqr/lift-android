package com.gradation.lift.feature.updateRoutine.common.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * [currentRoutineSetRoutineState] 현재 루틴에 대한 상태
 * [routineSetId] 수정할 원본 루틴의 아이디
 * [routineUiState] 수정할 원본 루틴의 상태 (해당 상태를 바탕으로 성공적으로 불러와 졌는지 판단 가능)
 * @since 2023-12-06 16:56:43
 */
@HiltViewModel
class UpdateRoutineSharedViewModel @Inject constructor(
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
) : ViewModel() {

    internal val currentRoutineSetRoutineState =
        CurrentRoutineSetRoutineState(viewModelScope = viewModelScope)


    private var routineSetId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val setRoutineSetId: (Int?) -> Unit = { routineSetId.value = it }

    val routineUiState: StateFlow<RoutineUiState> =
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
                        currentRoutineSetRoutineState.currentRoutineSetRoutine.update { this.first() }
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

