package com.gradation.lift.feature.routine_detail.routine_list.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineDetailRoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListInfoState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.routineDetailRoutineListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RoutineListViewModel @Inject constructor(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase
) : ViewModel() {

    val sortFilterState = SortFilterState()
    val routineListInfoState: RoutineListInfoState = RoutineListInfoState()

    val routineSetRoutineList: StateFlow<RoutineDetailRoutineListUiState> =
        routineDetailRoutineListUiState(
            getRoutineSetRoutineUseCase,
            sortFilterState,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineDetailRoutineListUiState.Loading
        )

}


