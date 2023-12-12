package com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.work.GetPopularWorkCategoryUseCase
import com.gradation.lift.domain.usecase.work.GetRecommendWorkCategoryUseCase
import com.gradation.lift.domain.usecase.work.GetWorkCategoryUseCase
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.model.TagWorkCategory
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

fun workCategoryUiState(
    workPartFilter: MutableStateFlow<Set<WorkPart>>,
    searchFilterText: MutableStateFlow<String>,
    getWorkCategoryUseCase: GetWorkCategoryUseCase,
    getPopularWorkCategoryUseCase: GetPopularWorkCategoryUseCase,
    getRecommendWorkCategoryUseCase: GetRecommendWorkCategoryUseCase
): Flow<WorkCategoryUiState> {
    return combine(
        workPartFilter,
        searchFilterText,
        getWorkCategoryUseCase(),
        getPopularWorkCategoryUseCase(),
        getRecommendWorkCategoryUseCase()

    ) { workPart,
        text,
        workCategoryList,
        popularWorkCategoryList,
        recommendWorkCategoryList ->
        when (popularWorkCategoryList) {
            is DataState.Fail -> WorkCategoryUiState.Fail(popularWorkCategoryList.message)
            is DataState.Success -> {
                when (recommendWorkCategoryList) {
                    is DataState.Fail -> WorkCategoryUiState.Fail(recommendWorkCategoryList.message)
                    is DataState.Success -> {
                        when (workCategoryList) {
                            is DataState.Fail -> WorkCategoryUiState.Fail(workCategoryList.message)
                            is DataState.Success -> {
                                WorkCategoryUiState.Success(
                                    workCategoryList.data
                                        .filter { workCategory ->
                                            text.isEmpty() || workCategory.name.contains(
                                                text
                                            )
                                        }
                                        .filter { workCategory ->
                                            workPart.isEmpty() || workPart.contains(
                                                workCategory.workPart
                                            )
                                        }.sortedBy { it.name }.map { workCategory ->
                                            TagWorkCategory(
                                                workCategory,
                                                popularWorkCategoryList.data.contains(
                                                    workCategory
                                                ),
                                                recommendWorkCategoryList.data.contains(
                                                    workCategory
                                                ),
                                            )
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed interface WorkCategoryUiState {
    data class Success(val workCategoryList: List<TagWorkCategory>) : WorkCategoryUiState
    data class Fail(val message: String) : WorkCategoryUiState
    data object Loading : WorkCategoryUiState
}