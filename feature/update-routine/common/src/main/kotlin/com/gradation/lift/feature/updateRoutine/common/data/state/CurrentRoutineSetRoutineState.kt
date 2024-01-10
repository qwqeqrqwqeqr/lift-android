package com.gradation.lift.feature.updateRoutine.common.data.state

import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.routineSetDescriptionValidator
import com.gradation.lift.common.utils.routineSetNameValidator
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class CurrentRoutineSetRoutineState(
    val currentRoutineSetRoutine: MutableStateFlow<RoutineSetRoutine> = MutableStateFlow(
        RoutineSetRoutine()
    ),
    val viewModelScope: CoroutineScope,
) {
    var routineSetNameValidator: StateFlow<Validator> =
        currentRoutineSetRoutine.map { it ->
            if (it.name.isBlank()) {
                Validator(true, "")
            } else if (!routineSetNameValidator(it.name)) {
                Validator(false, "1 - 10자 사이의 글자로 입력해주세요.")
            } else {
                Validator(true, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )

    var routineSetDescriptionValidator: StateFlow<Validator> =
        currentRoutineSetRoutine.map { it ->
            if (!routineSetDescriptionValidator(it.description)) {
                Validator(false, "20자 내로 입력해주세요.")
            } else {
                Validator(true, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )

    var updateCondition: StateFlow<Boolean> =
        combine(
            routineSetNameValidator,
            routineSetDescriptionValidator,
            currentRoutineSetRoutine
        ) { e1, e2, e3 ->
            e1.status && e2.status && e3.routine.isNotEmpty()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )


    val appendRoutine: (Routine) -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.AppendRoutine(it))
    }

    val removeRoutine: (Routine) -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.RemoveRoutine(it))
    }

    val updateRoutine: (Int,Routine) -> Unit = {index,routine ->
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.UpdateRoutine(index,routine))
    }

    val updateRoutineSetName: (String) -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.UpdateRoutineSetName(it))
    }

    val clearRoutineSetName: () -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.ClearRoutineSetName)
    }

    val updateRoutineSetDescription: (String) -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.UpdateRoutineSetDescription(it))
    }

    val clearRoutineSetDescription: () -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.ClearRoutineSetDescription)
    }

    val updateRoutineSetWeekday: (Set<Weekday>) -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.UpdateRoutineSetWeekday(it))
    }

    val updateRoutineSetProfilePicture: (String) -> Unit = {
        onCurrentRoutineSetRoutineEvent(
            CurrentRoutineSetRoutineEvent.UpdateRoutineSetProfilePicture(
                it
            )
        )
    }

    val updateRoutineSetLabel: (Set<Label>) -> Unit = {
        onCurrentRoutineSetRoutineEvent(CurrentRoutineSetRoutineEvent.UpdateRoutineSetLabel(it))
    }

    private fun onCurrentRoutineSetRoutineEvent(currentRoutineSetRoutineEvent: CurrentRoutineSetRoutineEvent) {
        when (currentRoutineSetRoutineEvent) {
            is CurrentRoutineSetRoutineEvent.AppendRoutine -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    routine = currentRoutineSetRoutine.value.routine.plus(
                        currentRoutineSetRoutineEvent.routine
                    )
                )
            }

            is CurrentRoutineSetRoutineEvent.RemoveRoutine -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    routine = currentRoutineSetRoutine.value.routine.minus(
                        currentRoutineSetRoutineEvent.routine
                    )
                )
            }

            is CurrentRoutineSetRoutineEvent.UpdateRoutineSetDescription -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    description = currentRoutineSetRoutineEvent.description
                )
            }

            is CurrentRoutineSetRoutineEvent.UpdateRoutineSetLabel -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    label = currentRoutineSetRoutineEvent.label
                )
            }

            is CurrentRoutineSetRoutineEvent.UpdateRoutineSetProfilePicture -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    picture = currentRoutineSetRoutineEvent.profilePicture
                )
            }

            is CurrentRoutineSetRoutineEvent.UpdateRoutineSetName -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    name = currentRoutineSetRoutineEvent.name
                )
            }

            is CurrentRoutineSetRoutineEvent.UpdateRoutineSetWeekday -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    weekday = currentRoutineSetRoutineEvent.weekday
                )
            }

            CurrentRoutineSetRoutineEvent.ClearRoutineSetDescription -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    description = ""
                )
            }

            CurrentRoutineSetRoutineEvent.ClearRoutineSetName -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    name = ""
                )
            }

            is CurrentRoutineSetRoutineEvent.UpdateRoutine -> {
                currentRoutineSetRoutine.value = currentRoutineSetRoutine.value.copy(
                    routine = with(currentRoutineSetRoutine.value.routine) {
                        val mutableList = this.toMutableList()
                        mutableList[currentRoutineSetRoutineEvent.index] = currentRoutineSetRoutineEvent.routine
                        mutableList.toList()
                    }
                )
            }
        }
    }
}