package com.gradation.lift.feature.analytics.analytics.data.state

import com.gradation.lift.feature.analytics.analytics.data.model.WorkFrequencyMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import javax.inject.Inject

/**
 * [WorkCountByMonthAnalyticsState]
 * 월 별 운동 횟수 분석 상태
 * @property historyCountByPreMonth 지난 달의 총 운동횟수
 * @property historyCountByCurrentMonth 이번 달의 총 운동횟수
 * @property historyCountByMonthList 현재부터 이전 N월 동안 운동 횟수 (N은 [ANALYTICS_PERIOD] 로 판단함) 현재는 6개월 이전 데이터를 불러옴
 * @property historyAverageCurrentCount 현재부터 이전 N월 동안 평균 운동 횟수 (N은 [ANALYTICS_PERIOD] 로 판단함)
 * @property historyAveragePreCount 지난 달부터 이전 N+1월 동안 평균 운동 횟수 과거 평균운동횟수를 의미한다 (N은 [ANALYTICS_PERIOD] 로 판단함)
 * @since 2023-09-05 15:17:48
 */

class WorkCountByMonthAnalyticsState @Inject constructor(
    viewModelScope: CoroutineScope,
    today: StateFlow<LocalDate>,
    historyUiState: StateFlow<HistoryUiState>,
) {

    val historyCountByPreMonth: StateFlow<Int> = historyUiState.map { historyUiStateResult ->
        if (historyUiStateResult is HistoryUiState.Success) {
            historyUiStateResult.historyList.count { history ->
                history.historyTimeStamp.month == today.value.minus(DatePeriod(0, 1, 0)).month
            }
        } else 0
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
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


    val historyCountByMonthList: StateFlow<List<WorkFrequencyMonth>> =
        historyUiState.map { historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                (0..ANALYTICS_PERIOD).map {
                    today.value.minus(DatePeriod(0, it, 0)).month.let {
                        WorkFrequencyMonth(month = it.value,
                            frequency = historyUiStateResult.historyList.count { history ->
                                history.historyTimeStamp.month == it
                            })
                    }
                }.reversed()
            } else emptyList()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val historyAverageCurrentCount: StateFlow<Int> =
        historyCountByMonthList.map { it.map { it.frequency }.average().toInt() }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )


    val historyAveragePreCount: StateFlow<Int> = historyUiState.map { historyUiStateResult ->
        if (historyUiStateResult is HistoryUiState.Success) {
            (1..ANALYTICS_PERIOD + 1).map {
                historyUiStateResult.historyList.count { history ->
                    history.historyTimeStamp.month == today.value.minus(DatePeriod(0, it, 0)).month
                }
            }.average().toInt()
        } else 0
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )


    companion object {
        const val ANALYTICS_PERIOD = 6
    }
}