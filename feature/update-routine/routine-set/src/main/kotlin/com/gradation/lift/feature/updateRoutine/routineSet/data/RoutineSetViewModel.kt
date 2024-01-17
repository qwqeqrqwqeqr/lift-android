package com.gradation.lift.feature.updateRoutine.routineSet.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.DeleteRoutineSetRoutineUseCase
import com.gradation.lift.domain.usecase.routine.UpdateRoutineSetRoutineUseCase
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.UpdateRoutineState
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [RoutineSetViewModel]
 * @property updateRoutineState 루틴 삭제 및 수정에 대한 상태
 * @property updateUpdateRoutineState 상태 업데이트 메서드
 * @property deleteRoutineSetRoutine 루틴 수정
 * @property updateRoutineSetRoutine 루틴 삭제
 * @since 2023-12-06 11:45:01
 */
@HiltViewModel
class RoutineSetViewModel @Inject constructor(
    private val deleteRoutineSetRoutineUseCase: DeleteRoutineSetRoutineUseCase,
    private val updateRoutineSetRoutineUseCase: UpdateRoutineSetRoutineUseCase
) : ViewModel() {

    var updateRoutineState: MutableStateFlow<UpdateRoutineState> =
        MutableStateFlow(UpdateRoutineState.None)

    fun updateUpdateRoutineState(): (UpdateRoutineState) -> Unit = { updateRoutineState.value = it }

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
                            updateRoutineState.value = UpdateRoutineState.Success(true)
                        }
                    }
                }
        }
    }


    fun updateRoutineSetRoutine(): (RoutineSetRoutine) -> Unit = {
        viewModelScope.launch {
            updateRoutineSetRoutineUseCase(
                UpdateRoutineSetRoutine(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    weekday = it.weekday,
                    label = it.label,
                    picture = it.picture,
                    routine = it.routine.map { routine ->
                        UpdateRoutine(
                            id = routine.id,
                            workCategory = routine.workCategory.name,
                            workSetList = routine.workSetList
                        )
                    },
                )
            )
                .collect { deleteRoutineSetRoutineResult ->
                    when (deleteRoutineSetRoutineResult) {
                        is DataState.Fail -> {
                            updateRoutineState.value =
                                UpdateRoutineState.Fail(deleteRoutineSetRoutineResult.message)
                        }

                        is DataState.Success -> {
                            updateRoutineState.value = UpdateRoutineState.Success(false)
                        }
                    }
                }
        }
    }


}
