package com.gradation.lift.feature.analytics.analytics.data.state

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus

/**
 * [AnalyticsBarChartState]
 * 바 차트에서 사용되는 상태 값 집합
 * @param workCountByMonthList 최근 6개월 기준 월에 따른 운동 횟수
 * @param thisMonthWorkCountForPreMonth 지난달 대비 이번달 운동횟수 (부호가 있는 정수값으로 UI에서 핸들링 요구)
 * @since 2024-02-21 16:30:34
 */
class AnalyticsBarChartState(
    private val historyUiState: StateFlow<HistoryUiState>,
    private val selectedDate: MutableStateFlow<LocalDate>,
    private val viewModelScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider,
    val workCountByMonthList: StateFlow<List<WorkCountByMonth>> =
        combine(historyUiState, selectedDate) { state, date ->
            when (state) {
                is HistoryUiState.Fail -> emptyList()
                HistoryUiState.Loading -> emptyList()
                HistoryUiState.Empty -> emptyList()
                is HistoryUiState.Success -> {

                    val targetMonthRange: List<LocalDate> = (5 downTo 0).map { month ->
                        date.minus(DatePeriod(months = month))
                            .let { LocalDate(it.year, it.monthNumber, 1) }
                    }

                    targetMonthRange.map { targetDate ->
                        state.historyList.filter { history ->
                            history.historyTimeStamp.monthNumber == targetDate.monthNumber &&
                                    history.historyTimeStamp.year == targetDate.year
                        }.let {
                            WorkCountByMonth(
                                workCount = it.size, month = targetDate
                            )

                        }
                    }
                }

            }
        }.flowOn(dispatcherProvider.default).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        ),
    val thisMonthWorkCountForPreMonth: StateFlow<Int> =
        workCountByMonthList.map {
            if (it.isEmpty()) 0 else it[it.lastIndex].workCount - it[it.lastIndex - 1].workCount
        }.flowOn(dispatcherProvider.default).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 0
        ),

    )