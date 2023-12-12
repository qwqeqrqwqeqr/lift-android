package com.gradation.lift.feature.history.analytics.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.domain.usecase.date.GetPreWeekUseCase
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.feature.history.analytics.data.state.HistoryUiState
import com.gradation.lift.feature.history.analytics.data.state.WorkCountByMonthAnalyticsState
import com.gradation.lift.feature.history.analytics.data.state.WorkFrequencyAnalyticsState
import com.gradation.lift.feature.history.analytics.data.state.WorkPartAnalyticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import javax.inject.Inject

/**
 * [HistoryAnalyticsViewModel]
 * @property today 현재 날짜
 * @property historyUiState 운동 기록 관련 상태
 * @since 2023-09-05 19:48:50
 */

@HiltViewModel
class HistoryAnalyticsViewModel @Inject constructor(
    getHistoryUseCase: GetHistoryUseCase,
    getTodayUseCase: GetTodayUseCase,
    getWeekDateOfCurrentMonthUseCase: GetWeekDateOfCurrentMonthUseCase,
    getPreWeekUseCase: GetPreWeekUseCase,
    getCurrentWeekUseCase: GetCurrentWeekUseCase,
) : ViewModel() {

    private val today: StateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())

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



    val workFrequencyAnalyticsState = WorkFrequencyAnalyticsState(
        viewModelScope,
        historyUiState,
        getTodayUseCase,
        getWeekDateOfCurrentMonthUseCase
    )

    val workCountByMonthAnalyticsState = WorkCountByMonthAnalyticsState(
        viewModelScope,
        today,
        historyUiState
    )


    val workPartAnalyticsState = WorkPartAnalyticsState(
        viewModelScope,
        today,
        historyUiState,
        getPreWeekUseCase,
        getCurrentWeekUseCase
    )


}






