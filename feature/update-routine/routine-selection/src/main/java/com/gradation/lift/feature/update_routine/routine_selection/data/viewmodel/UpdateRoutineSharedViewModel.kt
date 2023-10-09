package com.gradation.lift.feature.update_routine.routine_selection.data.viewmodel

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
 * @constructor getCurrentWeekUseCase 이번주 날짜 정보를 불러오는 유즈케이스
 * @property selectedRoutineSetRoutine 현재 수정을 하기 위해 선택된 루틴
 * @property tempWorkCategory 현재 수정을 하기 위해 선택된 루틴
 * @property routineSetNameValidator 루틴 세트 이름 유효성 검사
 * @property routineSetDescriptionValidator 루틴 세트 설명 유효성 검사
 * @property weekDateSelectionList 요일 및 날짜 정보와 해당 요일을 뷰에서 선택했는지에 대한 정보를 가지고 있음
 * @property navigationCondition 네비게이션 조건
 * @property updateSelectedRoutineSetRoutineWithRoutineSelection 루틴 선택 화면에서 선택된 루틴을 바탕으로 업데이트할 때 사용할 것
 * @property appendRoutine 루틴 세트 내 루틴 추가
 * @property removeRoutine 루틴 세트 내 루틴 삭제
 * @since 2023-09-16 16:27:34
 */
@HiltViewModel

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
                Validator(false, "20자 내로 입력해주세요.")
            } else {
                Validator(true, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    
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
        combine(
            routineSetNameValidator,
            routineSetDescriptionValidator,
            selectedRoutineSetRoutine
        ) { e1, e2, e3 ->
            e1.status && e2.status && e3.routine.isNotEmpty()
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