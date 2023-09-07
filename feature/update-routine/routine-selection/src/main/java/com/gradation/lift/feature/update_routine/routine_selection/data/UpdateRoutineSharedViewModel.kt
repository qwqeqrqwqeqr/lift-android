package com.gradation.lift.feature.update_routine.routine_selection.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSetRoutineSelection
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [UpdateRoutineSharedViewModel]
 * @property selectedRoutineSetRoutine 현재 수정을 하기 위해 선택된 루틴
 *
 * @property [updateSelectedRoutineSetRoutineWithRoutineSelection] 루틴 선택 화면에서 선택된 루틴을 바탕으로 업데이트할 때 사용할 것
 * @property [updateSelectedRoutineSetRoutine] 루틴 수정 화면에서 루틴 세트 자체 업데이트
 * @property [updateRoutine] 루틴 세트 내 루틴 자체 업데이트
 * @property [appendRoutine] 루틴 세트 내 루틴 추가
 * @property [removeRoutine] 루틴 세트 내 루틴 삭제
 * @since 2023-09-06 21:07:27
 */
@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class UpdateRoutineSharedViewModel @Inject constructor(
) : ViewModel() {


    val selectedRoutineSetRoutine: MutableStateFlow<UpdateRoutineSetRoutine> = MutableStateFlow(
        UpdateRoutineSetRoutine()
    )


    fun updateSelectedRoutineSetRoutineWithRoutineSelection(): (RoutineSetRoutineSelection) -> Unit =
        {
            selectedRoutineSetRoutine.value = UpdateRoutineSetRoutine(
                id = it.id,
                name = it.name,
                description = it.description,
                weekday = it.weekday,
                picture = it.picture,
                routine = it.routine.map { routineSelection ->
                    UpdateRoutine(
                        id = routineSelection.routine.id,
                        workCategory = routineSelection.routine.workCategory.name,
                        workSetList = routineSelection.routine.workSetList
                    )
                }
            )
        }

    fun updateSelectedRoutineSetRoutine(): (UpdateRoutineSetRoutine) -> Unit = {
        selectedRoutineSetRoutine.value = it
    }

    fun updateRoutine(): (UpdateRoutine) -> Unit = {routine ->
        selectedRoutineSetRoutine.update {
            it.copy(
                routine = it.routine.apply {
                    find { it.id==routine.id }?.copy(
                        workCategory = routine.workCategory,
                        workSetList = routine.workSetList
                    )
                }
            )
        }
    }

    fun appendRoutine(): (UpdateRoutine) -> Unit = { routine ->
        selectedRoutineSetRoutine.update {
            it.copy(
                routine = it.routine.plusElement(routine)
            )
        }
    }

    fun removeRoutine(): (UpdateRoutine) -> Unit = { routine ->
        if (selectedRoutineSetRoutine.value.routine.size > 1) {
            selectedRoutineSetRoutine.update {
                it.copy(
                    routine = it.routine.minusElement(routine)
                )
            }
        }
    }

}