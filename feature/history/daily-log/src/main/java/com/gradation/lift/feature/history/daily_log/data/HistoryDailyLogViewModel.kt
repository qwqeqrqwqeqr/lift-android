package com.gradation.lift.feature.history.daily_log.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.feature.history.daily_log.data.model.HistoryScoreWeekDate
import com.gradation.lift.feature.history.daily_log.data.state.HistoryUiState
import com.gradation.lift.model.model.history.History
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import javax.inject.Inject

/**
 * [HistoryDailyLogViewModel]
 * @property selectedDate 선택된 날짜, 해당 날짜를 바탕으로 히스토리 정보를 가져옴
 * @property historyUiState 운동 기록 관련 상태
 * @property selectedHistoryList 현재 선택된 날짜에 대한 히스토리 리스트
 * @property selectedHistory 현재 선택된 날짜에 대한 히스토리 리스트
 * @property historyScoreByMonth 한달 운동 기록에 대한 운동기록 점수
 * @since 2023-09-05 21:10:41
 */
@HiltViewModel
class HistoryDailyLogViewModel @Inject constructor(
    getHistoryUseCase: GetHistoryUseCase,
    getTodayUseCase: GetTodayUseCase,
    getWeekDateOfCurrentMonthUseCase: GetWeekDateOfCurrentMonthUseCase,
) : ViewModel() {

    val selectedDate: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())

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
    val selectedHistoryList: StateFlow<List<History>> =
        combine(selectedDate, historyUiState) { selectedDate, historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                historyUiStateResult.historyList.filter { it.historyTimeStamp.date == selectedDate }
            } else emptyList()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val selectedHistory = selectedHistoryList.map {
        if (it.isNotEmpty()) {
            it.first()
        } else {
            History()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = History()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val historyScoreByMonth: StateFlow<List<HistoryScoreWeekDate>> =
        combine(selectedDate, historyUiState) { selectedMonth, historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                getWeekDateOfCurrentMonthUseCase(selectedMonth).map { weekDateMonth ->
                    HistoryScoreWeekDate(
                        score = historyUiStateResult.historyList.filter {
                            it.historyTimeStamp.date == weekDateMonth.date
                        }.maxByOrNull { it.historyTimeStamp }?.score ?: 0,
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


    fun minusMonthSelectedDate(): () -> Unit = {
        selectedDate.value = selectedDate.value.minus(DatePeriod(0, 1, 0))
    }

    fun plusMonthSelectedDate(): () -> Unit = {
        selectedDate.value = selectedDate.value.plus(DatePeriod(0, 1, 0))
    }

    fun updateSelectedDate(): (LocalDate) -> Unit = {
        selectedDate.value = it
    }

}