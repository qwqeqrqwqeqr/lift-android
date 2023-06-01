package com.gradation.lift.feature.routine.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.date.GetCurrentDateUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class RoutineViewModel @Inject constructor(
    getWeekDateUseCase: GetWeekDateUseCase,
    getCurrentDateUseCase: GetCurrentDateUseCase,
    getRoutineSetRoutineByDateUseCase: GetRoutineSetRoutineByDateUseCase
) : ViewModel() {

    val currentDate by mutableStateOf<String>(getCurrentDateUseCase().let { "${it.monthNumber}월 ${it.dayOfMonth}일" })
    val weekDateRoutineUiState: StateFlow<WeekDateRoutineUiState> = weekDateRoutineUiState(
        getRoutineSetRoutineByDateUseCase = getRoutineSetRoutineByDateUseCase,
        getCurrentDateUseCase = getCurrentDateUseCase,
        getWeekDateUseCase = getWeekDateUseCase
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WeekDateRoutineUiState.Loading,
    )




}

