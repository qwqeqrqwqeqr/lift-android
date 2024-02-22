package com.gradation.lift.feature.analytics.analytics.data.state

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import com.gradation.lift.feature.analytics.analytics.data.model.DateHistory
import com.gradation.lift.feature.analytics.analytics.data.model.WeekDateHistoryCount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate

data class AnalyticsCalendarState(
    private val getWeekDateOfCurrentMonthUseCase: GetWeekDateOfCurrentMonthUseCase,
    private val selectedDate: MutableStateFlow<LocalDate>,
    private val historyUiState: StateFlow<HistoryUiState>,
    private val viewModelScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider,
    private val dateHistoryList: StateFlow<List<DateHistory>> = historyUiState.map {
        when (it) {
            is HistoryUiState.Fail -> emptyList()
            HistoryUiState.Loading -> emptyList()
            HistoryUiState.Empty -> emptyList()
            is HistoryUiState.Success -> {
                it.historyList.groupBy { history -> history.historyTimeStamp.date }.map { entry ->
                    DateHistory(
                        date = entry.key,
                        historyList = entry.value.sortedBy { history -> history.historyTimeStamp }
                    )
                }
            }
        }
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    ),
    val calendar: StateFlow<Map<Int, List<WeekDateHistoryCount>>> =
        combine(dateHistoryList, selectedDate) { dateHistoryList, selectedDate ->
            getWeekDateOfCurrentMonthUseCase(selectedDate).map { weekDate ->
                WeekDateHistoryCount(
                    historyCount = dateHistoryList.flatMap { it.historyList }
                        .count { weekDate.date == it.historyTimeStamp.date },
                    weekDateMonth = weekDate
                )
            }.groupBy { it.weekDateMonth.week }
        }.flowOn(dispatcherProvider.default).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyMap()
        ),
    val selectedDateHistoryCount: StateFlow<Int> = combine(
        historyUiState,
        selectedDate
    ) { state, date ->
        when (state) {
            is HistoryUiState.Fail -> 0
            HistoryUiState.Loading -> 0
            HistoryUiState.Empty -> 0
            is HistoryUiState.Success -> {
                state.historyList
                    .count { it.historyTimeStamp.year == date.year && it.historyTimeStamp.monthNumber == date.monthNumber }
            }
        }
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    ),
)
