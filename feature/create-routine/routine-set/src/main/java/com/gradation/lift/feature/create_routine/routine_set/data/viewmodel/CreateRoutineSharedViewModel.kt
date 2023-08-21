package com.gradation.lift.feature.create_routine.routine_set.data.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.*
import com.gradation.lift.domain.usecase.date.GetThisWeekUseCase
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import com.gradation.lift.feature.create_routine.routine_set.data.model.WeekdaySelection
import com.gradation.lift.feature.create_routine.routine_set.data.state.CreateRoutineState
import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.common.toWeekday
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.work.WorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * [CreateRoutineSharedViewModel]
 * @property routineSetName  루틴 세트에 사용할 이름 (1-10자)
 * @property routineSetDescription 루틴 세트에 대한 설명 (1-20자)
 * @property routineSetPicture 루틴 세트 사진
 * @property routineSetRoutine 루틴 세트에 포함될 루틴들
 * @property tempWorkCategory 루틴을 만들 때 필요한 카테고리 정보로 임시로 저장해두고 루틴을 생성할 때 사용한다.
 * @property routineSetNameValidator 루틴 세트 이름 유효성 검증
 * @property routineSetDescriptionValidator 루틴 세트 설명 유효성 검증
 * @property weekdaySelectionList 선택할 날짜 리스트 (월 - 일)
 * @property createRoutineCondition 루틴리스트를 생성할 조건 (true일 경우 루틴세트를 만들 수 있는 버튼이 활성화 된다.)
 * @property createRoutineState 루틴 생성 상태
 * @since 2023-08-20 14:34:55
 */
@HiltViewModel
class CreateRoutineSharedViewModel @Inject constructor(
    private val createRoutineSetUseCase: CreateRoutineSetUseCase,
    private val getThisWeekUseCase: GetThisWeekUseCase,
) : ViewModel() {


    val routineSetName: MutableStateFlow<String> = MutableStateFlow("")
    val routineSetDescription: MutableStateFlow<String> = MutableStateFlow("")
    private val routineSetWeekday: MutableStateFlow<List<Weekday>> =
        MutableStateFlow(emptyList<Weekday>())
    val routineSetPicture: MutableStateFlow<String> = MutableStateFlow("")
    val routineSetRoutine: MutableStateFlow<List<CreateRoutine>> =
        MutableStateFlow(emptyList<CreateRoutine>())

    val tempWorkCategory: MutableStateFlow<String> = MutableStateFlow("")

    var routineSetNameValidator: StateFlow<Validator> =
        routineSetName.map { it ->
            if (it.isBlank()) {
                Validator(false, "")
            } else if (!routineSetNameValidator(it)) {
                Validator(false, "1 - 10자 사이로 입력해주세요.")
            } else {
                Validator(false, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var routineSetDescriptionValidator: StateFlow<Validator> =
        routineSetDescription.map { it ->
            if (it.isBlank()) {
                Validator(false, "")
            } else if (!routineSetDescriptionValidator(it)) {
                Validator(false, "1 - 20자 사이로 입력해주세요.")
            } else {
                Validator(false, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    @RequiresApi(Build.VERSION_CODES.O)
    internal val weekdaySelectionList: StateFlow<List<WeekdaySelection>> =
        routineSetWeekday.map { weekdayList ->
            getThisWeekUseCase().map { localDate ->
                WeekdaySelection(
                    weekday = localDate.toWeekday(),
                    selected = localDate.toWeekday() in weekdayList
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    val createRoutineCondition = combine(
        routineSetNameValidator,
        routineSetDescriptionValidator,
        routineSetPicture,
        routineSetWeekday,
        routineSetRoutine
    ) {
            routineSetNameValidator,
            routineSetDescriptionValidator,
            routineSetPicture,
            routineSetWeekday,
            routineSetRoutine,
        ->
        routineSetNameValidator.status &&
                routineSetDescriptionValidator.status &&
                routineSetPicture.isNotEmpty() &&
                routineSetWeekday.isNotEmpty() &&
                routineSetRoutine.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    val createRoutineState: MutableStateFlow<CreateRoutineState> =
        MutableStateFlow(CreateRoutineState.None)


    fun updateRoutineSetName(): (String) -> Unit = {
        routineSetName.value = it
    }

    fun updateRoutineSetDescription(): (String) -> Unit = {
        routineSetDescription.value = it
    }

    fun updateRoutineSetPicture(): (String) -> Unit = {
        routineSetPicture.value = it
    }

    fun updateRoutineSetWeekday(): (Weekday) -> Unit = { value ->
        if (value in routineSetWeekday.value) {
            routineSetWeekday.update { it.minusElement(value) }
        } else {
            routineSetWeekday.update { it.plusElement(value) }
        }
    }

    fun updateTempWorkCategory(): (String) -> Unit = {
        tempWorkCategory.value = it
    }


    fun addRoutine(): (List<WorkSet>) -> Unit = { workSet ->

        routineSetRoutine.update {
            it.plusElement(
                CreateRoutine(
                    tempWorkCategory.value,
                    workSet
                )
            )
        }
    }

    fun removeRoutine(): (CreateRoutine) -> Unit = { createRoutine ->
        routineSetRoutine.update { it.minusElement(createRoutine) }
    }

    fun updateCreateRoutineState(): (CreateRoutineState) -> Unit = {
        createRoutineState.value = it
    }

    fun createRoutineSet(): () -> Unit = {
        viewModelScope.launch {
            createRoutineSetUseCase(
                CreateRoutineSetRoutine(
                    routineSetName.value,
                    routineSetDescription.value,
                    routineSetWeekday.value,
                    routineSetPicture.value,
                    routineSetRoutine.value
                )
            ).collect { createRoutineResult ->
                when (createRoutineResult) {
                    is DataState.Success -> {
                        CreateRoutineState.Success
                    }
                    is DataState.Fail -> {
                        CreateRoutineState.Fail(createRoutineResult.message)
                    }
                }
            }
        }
    }
}





