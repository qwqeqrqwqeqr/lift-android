package com.gradation.lift.feature.history.analytics.data.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import com.gradation.lift.feature.history.analytics.data.model.WorkFrequencyWeekDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import javax.inject.Inject

/**
 * [WorkFrequencyAnalyticsState]
 * 운동 빈도 분석 상태
 * @property selectedDate 선택된 월, 해당 월을 기준으로 빈도 수를 분석
 * @property historyCountByThisMonth '현재 사용자의 기준 월' 종합 운동 빈도 수
 * @property workFrequencyByWeek 선택된 월 기준 주차별 빈도수
 */
class WorkFrequencyAnalyticsState @Inject constructor(
    viewModelScope: CoroutineScope,
    today: StateFlow<LocalDate>,
    historyUiState: StateFlow<HistoryUiState>,
    getTodayUseCase: GetTodayUseCase,
    private val getWeekDateOfCurrentMonthUseCase: GetWeekDateOfCurrentMonthUseCase,
) {
    val selectedMonth: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())

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

    @RequiresApi(Build.VERSION_CODES.O)
    val workFrequencyByWeek: StateFlow<List<WorkFrequencyWeekDate>> =
        combine(selectedMonth, historyUiState) { selectedMonth, historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                getWeekDateOfCurrentMonthUseCase(selectedMonth).map { weekDateMonth ->
                    WorkFrequencyWeekDate(
                        frequency = historyUiStateResult.historyList.count {
                            it.historyTimeStamp.date == weekDateMonth.date
                        },
                        week = weekDateMonth.week,
                        weekday = weekDateMonth.weekday,
                        date = weekDateMonth.date
                    )
                }

            } else emptyList()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun minusSelectedMonth(): () -> Unit = {
        selectedMonth.value = selectedMonth.value.minus(DatePeriod(0,1,0))
    }
    fun plusSelectedMonth(): () -> Unit = {
        selectedMonth.value = selectedMonth.value.plus(DatePeriod(0,1,0))
    }


}
