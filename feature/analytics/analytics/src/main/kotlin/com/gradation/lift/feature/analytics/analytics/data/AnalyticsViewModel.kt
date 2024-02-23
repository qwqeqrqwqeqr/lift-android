package com.gradation.lift.feature.analytics.analytics.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.common.DispatcherProvider
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
import kotlinx.coroutines.flow.flowOn
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
    dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    val today: LocalDate = getTodayUseCase()

    val historyUiState: StateFlow<HistoryUiState> = getHistoryUseCase().map {
        when (it) {
            is DataState.Fail -> HistoryUiState.Fail(it.message)
            is DataState.Success ->
                if (it.data.isEmpty()) HistoryUiState.Empty
                else HistoryUiState.Success(it.data)
        }
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HistoryUiState.Loading
    )

    val selectedDate: MutableStateFlow<LocalDate> = MutableStateFlow(today)


    val analyticsCalendarState = AnalyticsCalendarState(
        getWeekDateOfCurrentMonthUseCase,
        selectedDate,
        historyUiState,
        viewModelScope,
        dispatcherProvider
    )

    val analyticsBarChartState = AnalyticsBarChartState(
        historyUiState,
        selectedDate,
        viewModelScope,
        dispatcherProvider
    )

    val analyticsHexagonChartState = AnalyticsHexagonChartState(
        historyUiState,
        selectedDate,
        viewModelScope,
        dispatcherProvider
    )

    val analyticsPieChartState = AnalyticsPieChartState(
        historyUiState,
        selectedDate,
        viewModelScope,
        dispatcherProvider
    )


    val updateSelectedDate: (LocalDate) -> Unit = {
        selectedDate.value = it
    }

}






