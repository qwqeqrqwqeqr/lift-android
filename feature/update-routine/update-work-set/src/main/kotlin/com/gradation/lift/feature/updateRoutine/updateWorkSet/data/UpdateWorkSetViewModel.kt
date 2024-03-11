package com.gradation.lift.feature.updateRoutine.updateWorkSet.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.favorite.GetWorkCategoryFavoriteUseCase
import com.gradation.lift.domain.usecase.favorite.UpdateWorkCategoryFavoriteUseCase
import com.gradation.lift.domain.usecase.workCategory.GetWorkCategoryByIdUseCase
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.model.CreateWorkSet
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.workCategoryUiState
import com.gradation.lift.model.model.routine.Routine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [UpdateWorkSetViewModel]
 * @since 2024-01-10 15:02:02
 */
@HiltViewModel
class UpdateWorkSetViewModel @Inject constructor(
    getWorkCategoryByIdUseCase: GetWorkCategoryByIdUseCase,
    getWorkCategoryFavoriteUseCase: GetWorkCategoryFavoriteUseCase,
    private val updateWorkCategoryFavoriteUseCase: UpdateWorkCategoryFavoriteUseCase,
) : ViewModel() {

    private var workCategoryId: MutableStateFlow<Int> = MutableStateFlow(0)

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

    val setRoutine: (Routine) -> Unit = { routine ->
        workSetState.createWorkSetLists.clear()
        routine.workSetList.forEachIndexed { index, workSet ->
            workSetState.createWorkSetLists.add(
                CreateWorkSet(
                    weight = workSet.weight.toString(),
                    repetition = workSet.repetition.toString(),
                )
            )
        }
        workCategoryId.update { routine.workCategoryId }
    }

    val updateWorkCategoryFavorite: () -> Unit = {
        viewModelScope.launch {
            workCategoryId.value.let { id ->
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
