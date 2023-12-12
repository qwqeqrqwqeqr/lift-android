package com.gradation.lift.feature.createRoutine.common.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [currentRoutineSetRoutineState] 현재 루틴에 대한 상태
 * @since 2023-12-06 16:56:43
 */
@HiltViewModel
class CreateRoutineSharedViewModel @Inject constructor(
) : ViewModel() {

    val currentRoutineSetRoutineState =
        CurrentRoutineSetRoutineState(viewModelScope = viewModelScope)

}

