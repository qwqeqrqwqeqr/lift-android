package com.gradation.lift.feature.update_routine.routine_selection.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.routineSetDescriptionValidator
import com.gradation.lift.common.utils.routineSetNameValidator
import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSetRoutineSelection
import com.gradation.lift.feature.update_routine.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.toWeekday
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.model.model.work.WorkSet
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
 * @since 2023-09-13 11:34:48
 */
@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class UpdateRoutineSharedViewModel @Inject constructor(
    private val getCurrentWeekUseCase: GetCurrentWeekUseCase
) : ViewModel() {


    val selectedRoutineSetRoutine: MutableStateFlow<UpdateRoutineSetRoutine> = MutableStateFlow(
        UpdateRoutineSetRoutine()
    )
    val tempWorkCategory: MutableStateFlow<String> = MutableStateFlow("")


    var routineSetNameValidator: StateFlow<Validator> =
        selectedRoutineSetRoutine.map { it ->
            if (it.name.isBlank()) {
                Validator(false, "")
            } else if (!routineSetNameValidator(it.name)) {
                Validator(false, "1 - 10자 사이의 글자로 입력해주세요.")
            } else {
                Validator(true, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var routineSetDescriptionValidator: StateFlow<Validator> =
        selectedRoutineSetRoutine.map { it ->
            if (it.description.isBlank()) {
                Validator(false, "")
            } else if (!routineSetDescriptionValidator(it.description)) {
                Validator(false, "1 - 20자 사이의 글자로 입력해주세요.")
            } else {
                Validator(true, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    @RequiresApi(Build.VERSION_CODES.O)
    val weekDateSelectionList: StateFlow<List<WeekDateSelection>> =
        selectedRoutineSetRoutine.map { routine ->
            getCurrentWeekUseCase().map { localDate ->
                WeekDateSelection(
                    weekday = localDate.toWeekday(),
                    selected = localDate.toWeekday() == routine.weekday
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val navigationCondition: StateFlow<Boolean> =
        combine(routineSetNameValidator, routineSetDescriptionValidator) { e1, e2 ->
            e1.status && e2.status
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
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

    fun updateRoutineSetName(): (String) -> Unit = {
        selectedRoutineSetRoutine.value = selectedRoutineSetRoutine.value.copy(
            name = it
        )
    }

    fun updateRoutineSetDescription(): (String) -> Unit = {
        selectedRoutineSetRoutine.value = selectedRoutineSetRoutine.value.copy(
            description = it
        )
    }

    fun updateRoutineSetPicture(): (String) -> Unit = {
        selectedRoutineSetRoutine.value = selectedRoutineSetRoutine.value.copy(
            picture = it
        )
    }

    fun updateRoutineSetWeekday(): (Weekday) -> Unit = {
        selectedRoutineSetRoutine.value = selectedRoutineSetRoutine.value.copy(
            weekday = it
        )
    }

    fun updateTempWorkCategory(): (String) -> Unit = {
        tempWorkCategory.value = it
    }


    fun updateRoutine(): (UpdateRoutine) -> Unit = { routine ->
        selectedRoutineSetRoutine.update {
            it.copy(
                routine = it.routine.apply {
                    find { it.id == routine.id }?.copy(
                        workCategory = routine.workCategory,
                        workSetList = routine.workSetList
                    )
                }
            )
        }
    }

    fun appendRoutine(): (List<WorkSet>) -> Unit = { workSetList ->
        selectedRoutineSetRoutine.update {
            it.copy(
                routine = it.routine.plusElement(
                    UpdateRoutine(
                        null,
                        workCategory = tempWorkCategory.value,
                        workSetList = workSetList
                    )
                )
            )
        }
    }

    fun removeRoutine(): (UpdateRoutine) -> Unit = { routine ->
        selectedRoutineSetRoutine.update {
            it.copy(
                routine = it.routine.minusElement(routine)
            )
        }
    }

}