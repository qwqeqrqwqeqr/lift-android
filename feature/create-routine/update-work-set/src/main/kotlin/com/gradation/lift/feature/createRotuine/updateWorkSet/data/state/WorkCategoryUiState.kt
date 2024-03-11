package com.gradation.lift.feature.createRotuine.updateWorkSet.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.favorite.GetWorkCategoryFavoriteUseCase
import com.gradation.lift.domain.usecase.workCategory.GetWorkCategoryByIdUseCase
import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

fun workCategoryUiState(
    workCategoryId: MutableStateFlow<Int>,
    workCategoryFavoriteFlag: MutableStateFlow<Boolean>,
    getWorkCategoryByIdUseCase: GetWorkCategoryByIdUseCase,
    getWorkCategoryFavoriteUseCase: GetWorkCategoryFavoriteUseCase,
): Flow<WorkCategoryUiState> = flow {

    workCategoryId.collect {
        it.let { workCategory ->
            getWorkCategoryByIdUseCase(workCategory).collect { result ->
                when (result) {
                    is DataState.Fail -> emit(WorkCategoryUiState.Fail("불러오기를 실패하였습니다."))
                    is DataState.Success -> {
                        getWorkCategoryFavoriteUseCase().collect { favoriteIdList ->
                            when (favoriteIdList) {
                                is DataState.Fail -> emit(WorkCategoryUiState.Fail("불러오기를 실패하였습니다."))
                                is DataState.Success -> {
                                    workCategoryFavoriteFlag.update {
                                        favoriteIdList.data.contains(
                                            workCategory
                                        )
                                    }
                                    emit(
                                        WorkCategoryUiState.Success(result.data)
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


sealed interface WorkCategoryUiState {
    data class Success(
        val workCategory: WorkCategory,
    ) : WorkCategoryUiState

    data class Fail(val message: String) : WorkCategoryUiState
    data object Loading : WorkCategoryUiState
}