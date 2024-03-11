package com.gradation.lift.feature.work.createWorkSet.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.favorite.GetWorkCategoryFavoriteUseCase
import com.gradation.lift.domain.usecase.favorite.UpdateWorkCategoryFavoriteUseCase
import com.gradation.lift.domain.usecase.workCategory.GetWorkCategoryByIdUseCase
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.work.createWorkSet.data.state.workCategoryUiState
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    getWorkCategoryFavoriteUseCase: GetWorkCategoryFavoriteUseCase,
    savedStateHandle: SavedStateHandle,
    private val updateWorkCategoryFavoriteUseCase: UpdateWorkCategoryFavoriteUseCase,
) : ViewModel() {


    private var workCategoryId: StateFlow<Int?> = savedStateHandle.getStateFlow(
        SavedStateHandleKey.CreateRoutine.CREATE_WORK_CATEGORY_ID_KEY,
        null
    )

    val workSetState = WorkSetState()

    val workCategoryFavoriteFlag: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val workCategoryUiState: StateFlow<WorkCategoryUiState> =
        workCategoryUiState(
            workCategoryId,
            workCategoryFavoriteFlag,
            getWorkCategoryByIdUseCase,
            getWorkCategoryFavoriteUseCase
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkCategoryUiState.Loading
        )

    val updateWorkCategoryFavorite: () -> Unit = {
        viewModelScope.launch {
            workCategoryId.value?.let { id ->
                updateWorkCategoryFavoriteUseCase(id).collect {
                    when (it) {
                        is DataState.Fail -> {}
                        is DataState.Success -> {
                            workCategoryFavoriteFlag.update { !workCategoryFavoriteFlag.value }
                        }
                    }
                }
            }

        }
    }

}


