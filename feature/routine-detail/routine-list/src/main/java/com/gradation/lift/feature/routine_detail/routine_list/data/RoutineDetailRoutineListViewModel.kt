package com.gradation.lift.feature.routine_detail.routine_list.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineUseCase
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineDetailRoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListInfoState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.routineDetailRoutineListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoutineDetailRoutineListViewModel @Inject constructor(
    getRoutineSetRoutineUseCase: GetRoutineSetRoutineUseCase
) : ViewModel() {

    private val _sortFilterState: MutableStateFlow<SortFilterState> =
        MutableStateFlow(SortFilterState())
    val sortFilterState: StateFlow<SortFilterState> = _sortFilterState.asStateFlow()


    private val _routineListInfoState: MutableStateFlow<RoutineListInfoState> =
        MutableStateFlow(RoutineListInfoState())
    val routineListInfoState: StateFlow<RoutineListInfoState> = _routineListInfoState.asStateFlow()


    val routineSetRoutineList: StateFlow<RoutineDetailRoutineListUiState> =
        routineDetailRoutineListUiState(
            getRoutineSetRoutineUseCase, sortFilterState
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineDetailRoutineListUiState.Loading
        )

}


