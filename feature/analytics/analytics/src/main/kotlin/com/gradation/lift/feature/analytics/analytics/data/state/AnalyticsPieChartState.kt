package com.gradation.lift.feature.analytics.analytics.data.state

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.model.model.history.HistoryRoutine
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate

/**
 * [AnalyticsPieChartState]
 * 파이 차트에서 사용되는 상태 값 집합
 * @param workPartList 운동 부위 목록
 * @param selectedWorkPart 현재 선택된(열람하고 싶은) 운동 부위
 * @param workCategoryCountByWorkPartList 운동부위에 대한 운동카테고리 리스트 (최대 4개만 출력)
 * @param sumOfWorkCountByWorkPart 해당 운동부위에 대한 운동 총합
 * @param mostUsedWorkCategory 가장 많이 사용한 운동부위
 * @since 2024-02-21 16:30:34
 */
class AnalyticsPieChartState(
    private val historyUiState: StateFlow<HistoryUiState>,
    private val selectedDate: MutableStateFlow<LocalDate>,
    private val viewModelScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider,
    private val selectedDateHistoryRoutine: StateFlow<List<HistoryRoutine>> = combine(
        historyUiState,
        selectedDate
    ) { state, date ->
        when (state) {
            is HistoryUiState.Fail -> emptyList()
            HistoryUiState.Loading -> emptyList()
            HistoryUiState.Empty -> emptyList()
            is HistoryUiState.Success -> {
                state.historyList
                    .filter { it.historyTimeStamp.year == date.year && it.historyTimeStamp.monthNumber == date.monthNumber }
                    .flatMap { it.historyRoutine }
            }
        }
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    ),
    val workPartList: List<String> = WorkPart.WORK_PART_NAME_LIST,
    val selectedWorkPart: MutableStateFlow<String> = MutableStateFlow(workPartList.first()),
    val workCategoryCountByWorkPartList: StateFlow<List<WorkCategoryCount>> = combine(
        selectedDateHistoryRoutine,
        selectedWorkPart
    ) { historyRoutine, workPart ->

        val filteredHistoryRoutine =
            historyRoutine.filter { it.workPart.contains(workPart) }

        filteredHistoryRoutine.groupBy { it.workCategoryName }.map { it.value }
            .sortedByDescending { it.size }.take(4).map {
                WorkCategoryCount(
                    name = it.first().workCategoryName,
                    count = it.size
                )
            }.toList()
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    ),
    val sumOfWorkCountByWorkPart: StateFlow<Int> = combine(
        selectedDateHistoryRoutine,
        selectedWorkPart
    ) { historyRoutine, workPart ->
        historyRoutine.count { it.workPart.contains(workPart) }
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 0
    ),
    val mostUsedWorkCategory: StateFlow<String> = workCategoryCountByWorkPartList.map {
        it.takeIf { it.isNotEmpty() }?.first()?.name ?: ""
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    ),
    val selectedDateTotalWorkCount: StateFlow<Int> = selectedDateHistoryRoutine.map {
        it.count()
    }.flowOn(dispatcherProvider.default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 0
    ),
) {
    val updateSelectedWorkPart: (String) -> Unit = { selectedWorkPart.value = it }
}