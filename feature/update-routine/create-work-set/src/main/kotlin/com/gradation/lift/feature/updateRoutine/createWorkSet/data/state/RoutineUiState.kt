package com.gradation.lift.feature.updateRoutine.createWorkSet.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.work.GetWorkCategoryByIdUseCase
import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

fun workCategoryUiState(
    workCategoryId: StateFlow<Int?>,
    getWorkCategoryByIdUseCase: GetWorkCategoryByIdUseCase
): Flow<WorkCategoryUiState> = flow {
    workCategoryId.collect {
        it?.let {
            getWorkCategoryByIdUseCase(it).collect { result ->
                when (result) {
                    is DataState.Fail -> emit(WorkCategoryUiState.Fail("불러오기를 실패하였습니다."))
                    is DataState.Success -> emit(WorkCategoryUiState.Success(result.data))
                }
            }
        } ?: emit(WorkCategoryUiState.Fail("불러오기를 실패하였습니다."))
    }
}


sealed interface WorkCategoryUiState {
    data class Success(val workCategory: WorkCategory) : WorkCategoryUiState
    data class Fail(val message: String) : WorkCategoryUiState
    data object Loading : WorkCategoryUiState
}