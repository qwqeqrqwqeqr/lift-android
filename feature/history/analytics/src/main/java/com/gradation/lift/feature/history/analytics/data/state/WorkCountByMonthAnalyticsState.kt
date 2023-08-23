package com.gradation.lift.feature.history.analytics.data.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * [WorkCountByMonthAnalyticsState]
 * 월 별 운동 횟수 분석 상태
 * @property historyCountByPreMonth 지난 달의 총 운동횟수
 * @property historyCountByCurrentMonth 이번달 총 운동횟수
 * @property historyCountByMonthList 현재부터 이전 N월 동안 운동 횟수 (N은 [ANALYTICS_PERIOD] 로 판단함) 현재는 6개월 이전 데이터를 불러옴
 * @property historyCountByMonthList 현재부터 이전 N월 동안 평균 운동 횟수 (N은 [ANALYTICS_PERIOD] 로 판단함)
 */
@RequiresApi(Build.VERSION_CODES.O)
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


    val historyCountByMonthList: StateFlow<List<Int>> = historyUiState.map { historyUiStateResult ->
        if (historyUiStateResult is HistoryUiState.Success) {
            (0..ANALYTICS_PERIOD).map {
                historyUiStateResult.historyList.count { history ->
                    history.historyTimeStamp.month == today.value.minus(DatePeriod(0, it, 0)).month
                }
            }
        } else emptyList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val historyAverageCount: StateFlow<Int> = historyCountByMonthList.map { it.average().roundToInt() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    companion object{
        const val ANALYTICS_PERIOD = 6
    }
}