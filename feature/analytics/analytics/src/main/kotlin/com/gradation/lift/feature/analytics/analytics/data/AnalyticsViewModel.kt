package com.gradation.lift.feature.analytics.analytics.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsBarChartState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsCalendarState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsHexagonChartState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsPieChartState
import com.gradation.lift.feature.analytics.analytics.data.state.HistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate
import javax.inject.Inject

/**
 * [AnalyticsViewModel]
 */

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    getHistoryUseCase: GetHistoryUseCase,
    getTodayUseCase: GetTodayUseCase,
    getWeekDateOfCurrentMonthUseCase: GetWeekDateOfCurrentMonthUseCase,
) : ViewModel() {

    val today: LocalDate = getTodayUseCase()

    val historyUiState: StateFlow<HistoryUiState> = getHistoryUseCase().map {
        when (it) {
            is DataState.Fail -> HistoryUiState.Fail(it.message)
            is DataState.Success -> HistoryUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HistoryUiState.Loading
    )

    val selectedDate: MutableStateFlow<LocalDate> = MutableStateFlow(today)


    val analyticsCalendarState = AnalyticsCalendarState(
        getWeekDateOfCurrentMonthUseCase,
        selectedDate,
        historyUiState,
        viewModelScope
    )

    val analyticsBarChartState = AnalyticsBarChartState(
        historyUiState,
        selectedDate,
        viewModelScope,
    )

    val analyticsHexagonChartState = AnalyticsHexagonChartState(
        historyUiState,
        selectedDate,
        viewModelScope
    )

    val analyticsPieChartState = AnalyticsPieChartState(
        historyUiState,
        selectedDate,
        viewModelScope
    )


    val updateSelectedDate: (LocalDate) -> Unit = {
        selectedDate.value = it
    }

}






