package com.gradation.lift.feature.update_routine.find_work_category.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.work.GetWorkCategoryUseCase
import com.gradation.lift.domain.usecase.work.GetWorkPartUseCase
import com.gradation.lift.feature.update_routine.find_work_category.data.model.WorkPartFilterSelection
import com.gradation.lift.feature.update_routine.find_work_category.data.state.SearchTextState
import com.gradation.lift.feature.update_routine.find_work_category.data.state.WorkPartFilterState
import com.gradation.lift.feature.update_routine.find_work_category.data.state.WorkPartFilterState.Companion.FILTER_ALL
import com.gradation.lift.model.model.work.WorkCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [UpdateRoutineFindWorkCategoryViewModel]
 * @property searchTextState 검색 텍스트 필드 상태
 * @property workPartFilterState 운동 부위 필터 상태 (현재 필터 상태를 나타냄, 기본 값은 전체)
 * @property workCategoryList 운동 카테고리 리스트
 * @property workPartFilterList 운동부위 필터 목록, 사용자가 필터링 할 수 있는 목록을 의미함
 * @property filteredWorkCategoryCount 필터링 된 운동 카테고리의 개수를 의미함
 * @since 2023-09-13 17:47:14
 */
@HiltViewModel
class UpdateRoutineFindWorkCategoryViewModel @Inject constructor(
    getWorkPartUseCase: GetWorkPartUseCase,
    getWorkCategoryUseCase: GetWorkCategoryUseCase,
) : ViewModel() {

    internal val searchTextState = SearchTextState()
    internal val workPartFilterState = WorkPartFilterState()

    val workCategoryList: StateFlow<List<WorkCategory>> =
        combine(
            workPartFilterState.workPartFilter,
            searchTextState.searchText,
            getWorkCategoryUseCase()
        ) { filter, searchText, workCategoryList ->
            when (workCategoryList) {
                is DataState.Fail -> emptyList<WorkCategory>()
                is DataState.Success -> workCategoryList.data
                    .filter { workCategory ->
                        searchText.isBlank() || workCategory.name.contains(
                            searchText
                        )
                    }
                    .filter { workCategory -> filter == FILTER_ALL || workCategory.workPart.name == filter }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    val workPartFilterList: StateFlow<List<WorkPartFilterSelection>> =
        combine(getWorkPartUseCase(), workPartFilterState.workPartFilter) { workPartList, filter ->
            when (workPartList) {
                is DataState.Fail -> emptyList()
                is DataState.Success -> {
                    listOf(
                        WorkPartFilterSelection(
                            workPart = FILTER_ALL,
                            selected = filter == FILTER_ALL
                        )
                    ).plus(
                        workPartList.data.map { workPart ->
                            WorkPartFilterSelection(
                                workPart = workPart.name,
                                selected = workPart.name == filter
                            )
                        }
                    )
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    val filteredWorkCategoryCount: StateFlow<Int> = workCategoryList.map { it.count() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )


}


