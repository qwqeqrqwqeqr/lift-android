package com.gradation.lift.feature.work.routineSelection.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.work.routineSelection.data.state.RoutineListUiState
import com.gradation.lift.feature.work.routineSelection.data.state.RoutineListInfoState
import com.gradation.lift.feature.work.routineSelection.data.state.SortFilterState
import com.gradation.lift.feature.work.routineSelection.data.state.routineDetailRoutineListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RoutineSelectionViewModel @Inject constructor(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase
) : ViewModel() {

    val sortFilterState: SortFilterState = SortFilterState()
    val routineListInfoState: RoutineListInfoState = RoutineListInfoState()

    val routineSetRoutineList: StateFlow<RoutineListUiState> =
        routineDetailRoutineListUiState(
            getRoutineSetRoutineUseCase,
            sortFilterState,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineListUiState.Loading
        )

}


