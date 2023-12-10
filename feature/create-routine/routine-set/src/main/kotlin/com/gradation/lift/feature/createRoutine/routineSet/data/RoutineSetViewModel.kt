package com.gradation.lift.feature.createRoutine.routineSet.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetRoutineUseCase
import com.gradation.lift.feature.createRoutine.routineSet.data.state.CreateRoutineState
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [RoutineSetViewModel]
 * @property createRoutineState 루틴 생성에 대한 상태
 * @property updateCreateRoutineState 상태 업데이트 메서드
 * @property createRoutineSetRoutine 루틴 생성
 * @since 2023-12-10 13:59:08
 */
@HiltViewModel
class RoutineSetViewModel @Inject constructor(
    private val createRoutineSetRoutineUseCase: CreateRoutineSetRoutineUseCase,
) : ViewModel() {

    var createRoutineState: MutableStateFlow<CreateRoutineState> =
        MutableStateFlow(CreateRoutineState.None)

    fun updateCreateRoutineState(): (CreateRoutineState) -> Unit = { createRoutineState.value = it }

    fun createRoutineSetRoutine(): (RoutineSetRoutine) -> Unit = {
        viewModelScope.launch {
            createRoutineSetRoutineUseCase(
                CreateRoutineSetRoutine(
                    name = it.name,
                    description = it.description,
                    weekday = it.weekday,
                    label = it.label,
                    picture = it.picture,
                    routine = it.routine.map { routine ->
                        CreateRoutine(
                            workCategory = routine.workCategory.name,
                            workSetList = routine.workSetList
                        )
                    },
                )
            )
                .collect { createRoutineSetRoutineResult ->
                    when (createRoutineSetRoutineResult) {
                        is DataState.Fail -> {
                            createRoutineState.value =
                                CreateRoutineState.Fail(createRoutineSetRoutineResult.message)
                        }

                        is DataState.Success -> {
                            createRoutineState.value = CreateRoutineState.Success
                        }
                    }
                }
        }
    }


}
