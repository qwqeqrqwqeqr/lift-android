package com.gradation.lift.feature.history.history.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfCurrentMonthUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.feature.history.history.data.model.DateHistory
import com.gradation.lift.feature.history.history.data.model.WeekDateHistoryCount
import com.gradation.lift.model.model.history.History
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate
import javax.inject.Inject

/**
 * [HistoryViewModel]
 * @property today 현재 날짜
 * @property selectedDate 선택된 날짜, 해당 날짜를 바탕으로 히스토리 정보를 가져옴
 * @property selectedHistoryContentIndex 선택된 날짜에 대한 운동기록 순번 (하루에 운동을 여러번 할 가능성에 대한 인덱스 기본값: 0)
 * @property calendar 캘린더 (선택한 날짜를 바탕으로 표시함)
 * @since 2024-01-26 16:20:01
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(
    getHistoryUseCase: GetHistoryUseCase,
    getTodayUseCase: GetTodayUseCase,
    getWeekDateOfCurrentMonthUseCase: GetWeekDateOfCurrentMonthUseCase,
) : ViewModel() {

    private val dateHistoryList: StateFlow<List<DateHistory>> = getHistoryUseCase().map {
        when (it) {
            is DataState.Fail -> {
                emptyList()
            }

            is DataState.Success -> {
                it.data.groupBy { history -> history.historyTimeStamp.date }.map { entry ->
                    DateHistory(
                        date = entry.key,
                        historyList = entry.value.sortedBy { history -> history.historyTimeStamp }
                    )
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )


    val today: LocalDate = getTodayUseCase()
    val selectedDate: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())
    val selectedHistoryContentIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val calendar: StateFlow<Map<Int, List<WeekDateHistoryCount>>> =
        combine(dateHistoryList, selectedDate) { dateHistoryList, selectedDate ->
            getWeekDateOfCurrentMonthUseCase(selectedDate).map { weekDate ->
                WeekDateHistoryCount(
                    historyCount = dateHistoryList.flatMap { it.historyList }
                        .count { weekDate.date == it.historyTimeStamp.date },
                    weekDateMonth = weekDate
                )
            }.groupBy { it.weekDateMonth.week }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyMap()
        )

    val selectedHistory: StateFlow<History?> = combine(
        selectedDate,
        selectedHistoryContentIndex,
        dateHistoryList
    ) { selectedDate, selectedHistoryContentIndex, dateHistoryList ->
        dateHistoryList.find { selectedDate == it.date }?.historyList?.get(
            selectedHistoryContentIndex
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )


    val updateSelectedDate: (LocalDate) -> Unit = {
        selectedHistoryContentIndex.value = 0
        selectedDate.value = it
    }
    val updateSelectedHistoryContentIndex: (Int) -> Unit = {
        selectedHistoryContentIndex.value = it
    }

}