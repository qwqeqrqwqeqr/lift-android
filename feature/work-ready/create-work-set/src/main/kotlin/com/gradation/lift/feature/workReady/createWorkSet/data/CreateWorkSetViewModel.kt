package com.gradation.lift.feature.workReady.createWorkSet.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.work.GetWorkCategoryByIdUseCase
import com.gradation.lift.feature.workReady.createWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.workReady.createWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.workReady.createWorkSet.data.state.workCategoryUiState
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [CreateWorkSetViewModel]
 * @property workCategoryId 선택된 운동 카테고리(종목) 아이디
 * @property workCategoryUiState 운동 카테고리 화면 상태
 * @since 2023-12-08 18:38:24
 */
@HiltViewModel
class CreateWorkSetViewModel @Inject constructor(
    getWorkCategoryByIdUseCase: GetWorkCategoryByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var workCategoryId: StateFlow<Int?> =
        savedStateHandle.getStateFlow<Int?>(
            SavedStateHandleKey.Work.WORK_READY_WORK_CATEGORY_ID_KEY,
            null
        )

    val workSetState = WorkSetState()


    val workCategoryUiState: StateFlow<WorkCategoryUiState> =
        workCategoryUiState(workCategoryId, getWorkCategoryByIdUseCase).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkCategoryUiState.Loading
        )


}


