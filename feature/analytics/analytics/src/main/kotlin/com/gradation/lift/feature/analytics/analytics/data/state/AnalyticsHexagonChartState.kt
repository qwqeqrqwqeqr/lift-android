package com.gradation.lift.feature.analytics.analytics.data.state

import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus

/**
 * [AnalyticsHexagonChartState]
 * 육각형 차트에서 사용되는 상태 값 집합
 * @param workPartCountByMonthList 지난달과 이번달에 대한 부위별 운동횟수
 * @param workCountByPreCurrentMonth 지난달과 이번달의 총 운동횟수
 * @since 2024-02-21 16:30:34
 */
class AnalyticsHexagonChartState(
    private val historyUiState: StateFlow<HistoryUiState>,
    private val selectedDate: MutableStateFlow<LocalDate>,
    private val viewModelScope: CoroutineScope,
    val workPartCountByMonthList: StateFlow<List<WorkPartCountByMonth>> = combine(
        historyUiState,
        selectedDate
    ) { state, date ->
        when (state) {
            is HistoryUiState.Fail -> emptyList()
            HistoryUiState.Loading -> emptyList()
            is HistoryUiState.Success -> {

                val targetMonthRange: List<LocalDate> = (0 until 2).map { month ->
                    date.minus(DatePeriod(months = month))
                        .let { LocalDate(it.year, it.monthNumber, 1) }
                }

                val historyListPair = targetMonthRange.map { targetDate ->
                    state.historyList.filter { history ->
                        history.historyTimeStamp.monthNumber == targetDate.monthNumber &&
                                history.historyTimeStamp.year == targetDate.year
                    }
                }.let { Pair(it[0], it[1]) }

                with(WorkPart.WORK_PART_NAME_LIST) {
                    map { workPart ->
                        WorkPartCountByMonth(
                            name = workPart,
                            preCount = historyListPair.first.flatMap { it.historyRoutine }
                                .count { it.workCategory.workPart.contains(workPart) },
                            currentCount = historyListPair.second.flatMap { it.historyRoutine }
                                .count { it.workCategory.workPart.contains(workPart) },
                        )
                    }
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    ),
    val workCountByPreCurrentMonth: StateFlow<List<WorkCountByMonth>> = combine(
        historyUiState,
        selectedDate
    ) { state, date ->
        when (state) {
            is HistoryUiState.Fail -> emptyList()
            HistoryUiState.Loading -> emptyList()
            is HistoryUiState.Success -> {

                val targetMonthRange: List<LocalDate> = (0 until 2).map { month ->
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
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    ),
)