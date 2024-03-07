package com.gradation.lift.feature.updateRoutine.findWorkCategory.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.workCategory.GetPopularWorkCategoryUseCase
import com.gradation.lift.domain.usecase.workCategory.GetRecommendWorkCategoryUseCase
import com.gradation.lift.domain.usecase.workCategory.GetWorkCategoryUseCase
import com.gradation.lift.domain.usecase.workPart.GetWorkPartUseCase
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FilterState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.WorkCategoryUiState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.workCategoryUiState
import com.gradation.lift.model.model.work.WorkPart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * [FindWorkCategoryViewModel]
 * @property filterState 필터 상태
 * @property workCategoryUiState 운동 카테고리 상태 (해당 상태에 따라 화면이 변화함)
 * @since 2023-12-07 12:06:56
 */
@HiltViewModel
internal class FindWorkCategoryViewModel @Inject constructor(
    getWorkPartUseCase: GetWorkPartUseCase,
    getWorkCategoryUseCase: GetWorkCategoryUseCase,
    getPopularWorkCategoryUseCase: GetPopularWorkCategoryUseCase,
    getRecommendWorkCategoryUseCase: GetRecommendWorkCategoryUseCase
) : ViewModel() {

    val filterState = FilterState()

    val workPartList: StateFlow<List<WorkPart>> = getWorkPartUseCase().map {
        when (it) {
            is DataState.Fail -> emptyList()
            is DataState.Success -> it.data
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val workCategoryUiState: StateFlow<WorkCategoryUiState> = workCategoryUiState(
        filterState.workPartFilter,
        filterState.searchFilterText,
        getWorkCategoryUseCase,
        getPopularWorkCategoryUseCase,
        getRecommendWorkCategoryUseCase
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WorkCategoryUiState.Loading
    )
}


