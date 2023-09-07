package com.gradation.lift.feature.update_routine.routine_set

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.DeleteRoutineSetRoutineUseCase
import com.gradation.lift.domain.usecase.routine.UpdateRoutineSetRoutineUseCase
import com.gradation.lift.feature.update_routine.routine_set.data.UpdateRoutineState
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [UpdateRoutineRoutineSetViewModel]
 * @property updateRoutineState 루틴세트 업데이트(수정 또는 삭제)와 관련 되어있는 상태
 * @constructor deleteRoutineSetRoutineUseCase 루틴세트 삭제 유즈케이스
 * @constructor updateRoutineSetRoutineUseCase 루틴세트 수정 유즈케이스
 * @since 2023-09-07 13:29:42
 */
@HiltViewModel
class UpdateRoutineRoutineSetViewModel @Inject constructor(
    private val deleteRoutineSetRoutineUseCase: DeleteRoutineSetRoutineUseCase,
    private val updateRoutineSetRoutineUseCase: UpdateRoutineSetRoutineUseCase
) : ViewModel() {

    var updateRoutineState: MutableStateFlow<UpdateRoutineState> =
        MutableStateFlow(UpdateRoutineState.None)


    fun updateUpdateRoutineState(): (UpdateRoutineState) -> Unit = {
        updateRoutineState.value = it
    }


    fun deleteRoutineSetRoutine(): (Int) -> Unit = {
        viewModelScope.launch {
            deleteRoutineSetRoutineUseCase(it)
                .collect { deleteRoutineSetRoutineResult ->
                    when (deleteRoutineSetRoutineResult) {
                        is DataState.Fail -> {
                            updateRoutineState.value =
                                UpdateRoutineState.Fail(deleteRoutineSetRoutineResult.message)
                        }

                        is DataState.Success -> {
                            updateRoutineState.value = UpdateRoutineState.Success
                        }
                    }
                }
        }
    }

    fun updateRoutineSetRoutine(): (UpdateRoutineSetRoutine) -> Unit = {
        viewModelScope.launch {
            updateRoutineSetRoutineUseCase(it)
                .collect { deleteRoutineSetRoutineResult ->
                    when (deleteRoutineSetRoutineResult) {
                        is DataState.Fail -> {
                            updateRoutineState.value =
                                UpdateRoutineState.Fail(deleteRoutineSetRoutineResult.message)
                        }

                        is DataState.Success -> {
                            updateRoutineState.value = UpdateRoutineState.Success
                        }
                    }
                }
        }
    }


}
