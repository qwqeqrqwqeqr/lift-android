package com.gradation.lift.feature.history.analytics.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.feature.history.analytics.data.state.HistoryUiState
import com.gradation.lift.feature.history.analytics.data.state.WorkCountByMonthAnalyticsState
import com.gradation.lift.feature.history.analytics.data.state.WorkFrequencyAnalyticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import javax.inject.Inject

/**
 * [HistoryAnalyticsViewModel]
 * @property historyCountByCurrentMonth '현재 사용자의 기준 월' 종합 운동 빈도 수
 */
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HistoryAnalyticsViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val getTodayUseCase: GetTodayUseCase,
    private val getWeekDateOfCurrentMonthUseCase: GetWeekDateOfCurrentMonthUseCase,
) : ViewModel() {

    val today: StateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())

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

    val historyCountByCurrentMonth: StateFlow<Int> = historyUiState.map { historyUiStateResult ->
        if (historyUiStateResult is HistoryUiState.Success) {
            historyUiStateResult.historyList.count { history ->
                history.historyTimeStamp.month == today.value.month
            }
        } else 0
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )


    val workFrequencyAnalyticsState = WorkFrequencyAnalyticsState(
        viewModelScope,
        today,
        historyUiState,
        getTodayUseCase,
        getWeekDateOfCurrentMonthUseCase
    )

    val workCountByMonthAnalyticsState = WorkCountByMonthAnalyticsState(
        viewModelScope, today, historyUiState
    )


}






