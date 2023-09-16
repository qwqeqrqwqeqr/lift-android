package com.gradation.lift.feature.update_routine.routine_selection.data.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * [OpenedRoutineState]
 * @property openedRoutineIdList 현재 오픈된 루틴 아이디 리스트
 * (루틴마다 아이디들은 고유의 번호를 가지고 있기 때문에 해당 로직이 정상적으로 동작함)
 * @since 2023-08-22 12:37:12
 */
class OpenedRoutineState {
    internal val openedRoutineIdList: MutableStateFlow<Set<Int>> = MutableStateFlow(emptySet())
    fun updateOpenedRoutineIdList(): (Int, Boolean) -> Unit = { id, checked ->
        if (checked) {
            openedRoutineIdList.update{ it.plusElement(id) }
        } else {
            openedRoutineIdList.update { it.minusElement(id) }
        }
    }
}