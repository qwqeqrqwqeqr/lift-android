package com.gradation.lift.feature.work.findWorkCategory.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.combine
import com.gradation.lift.domain.usecase.favorite.GetWorkCategoryFavoriteUseCase
import com.gradation.lift.domain.usecase.workCategory.GetPopularWorkCategoryUseCase
import com.gradation.lift.domain.usecase.workCategory.GetRecommendWorkCategoryUseCase
import com.gradation.lift.domain.usecase.workCategory.GetWorkCategoryUseCase
import com.gradation.lift.feature.work.findWorkCategory.data.model.TagWorkCategory
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

fun workCategoryUiState(
    workPartFilter: MutableStateFlow<Set<WorkPart>>,
    searchFilterText: MutableStateFlow<String>,
    favoriteFilter: MutableStateFlow<Boolean>,
    recommendFilter: MutableStateFlow<Boolean>,
    popularFilter: MutableStateFlow<Boolean>,
    getWorkCategoryUseCase: GetWorkCategoryUseCase,
    getPopularWorkCategoryUseCase: GetPopularWorkCategoryUseCase,
    getRecommendWorkCategoryUseCase: GetRecommendWorkCategoryUseCase,
    getWorkCategoryFavoriteUseCase: GetWorkCategoryFavoriteUseCase,
): Flow<WorkCategoryUiState> {
    return combine(
        workPartFilter,
        searchFilterText,
        favoriteFilter,
        recommendFilter,
        popularFilter,
        getWorkCategoryUseCase(),
        getPopularWorkCategoryUseCase(),
        getRecommendWorkCategoryUseCase(),
        getWorkCategoryFavoriteUseCase()
    ) {
            workPart,
            text,
            favorite,
            recommend,
            popular,
            workCategoryList,
            popularWorkCategoryList,
            recommendWorkCategoryList,
            favoriteIdList,
        ->
        when (popularWorkCategoryList) {
            is DataState.Fail -> WorkCategoryUiState.Fail(popularWorkCategoryList.message)
            is DataState.Success -> {
                when (recommendWorkCategoryList) {
                    is DataState.Fail -> WorkCategoryUiState.Fail(recommendWorkCategoryList.message)
                    is DataState.Success -> {
                        when (favoriteIdList) {
                            is DataState.Fail -> WorkCategoryUiState.Fail(favoriteIdList.message)
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
                                                    workPart.isEmpty() || workPart.map { it.name }
                                                        .intersect(workCategory.workPart.toSet())
                                                        .isNotEmpty()
                                                }
                                                .sortedBy { it.name }.map { workCategory ->
                                                    TagWorkCategory(
                                                        workCategory,
                                                        favoriteIdList.data.contains(workCategory.id),
                                                        popularWorkCategoryList.data.contains(
                                                            workCategory
                                                        ),
                                                        recommendWorkCategoryList.data.contains(
                                                            workCategory
                                                        ),
                                                    )
                                                }
                                                .filter { if (favorite) it.favoriteTag else true }
                                                .filter { if (recommend) it.recommendTag else true }
                                                .filter { if (popular) it.popularTag else true }
                                        )
                                    }
                                }
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