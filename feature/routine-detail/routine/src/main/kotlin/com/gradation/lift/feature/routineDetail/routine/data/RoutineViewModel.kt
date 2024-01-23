package com.gradation.lift.feature.routineDetail.routine.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var routineSetId : MutableStateFlow<Int?> = MutableStateFlow(savedStateHandle[DETAIL_ROUTINE_SET_ID_KEY])

    val routineSetRoutine: StateFlow<RoutineUiState> =
        routineUiState(routineSetId, getRoutineSetRoutineByRoutineSetIdUseCase).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineUiState.Loading
        )


}



