package com.gradation.lift.feature.history.analytics.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfThisMonthUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.feature.history.analytics.data.state.HistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import javax.inject.Inject

@HiltViewModel
class HistoryAnalyticsViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val getTodayUseCase: GetTodayUseCase,
    private val getWeekDateOfThisMonthUseCase: GetWeekDateOfThisMonthUseCase,
) : ViewModel() {

    val today: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())

    val historyUiState: StateFlow<HistoryUiState> = getHistoryUseCase().map {
        when (val getHistoryResult = it) {
            is DataState.Fail -> HistoryUiState.Fail(getHistoryResult.message)
            is DataState.Success -> {
                with(getHistoryResult.data) {
                    if (isEmpty()) HistoryUiState.Empty else HistoryUiState.Success(toList())
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HistoryUiState.None
    )




}





