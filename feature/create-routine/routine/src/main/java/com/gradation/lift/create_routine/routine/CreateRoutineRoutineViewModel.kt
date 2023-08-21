package com.gradation.lift.create_routine.routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.create_routine.routine.data.model.IndexWorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [CreateRoutineRoutineViewModel]
 * @property indexWorkSetList 운동 세트 리스트
 * @property createRoutineCondition 루틴생선 조건 해당 필드를 뷰와 연결시켜 제어함
 * @since 2023-08-21 23:15:09
 */
@HiltViewModel
class CreateRoutineRoutineViewModel @Inject constructor(
) : ViewModel() {


    val indexWorkSetList: MutableStateFlow<List<IndexWorkSet>> =
        MutableStateFlow(emptyList())

    val createRoutineCondition : StateFlow<Boolean> = indexWorkSetList.map { it.isNotEmpty() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    fun updateWorkSet(): (IndexWorkSet) -> Unit = { indexWorkSet ->
        indexWorkSetList.value = indexWorkSetList.value.map {
            it.copy(
                index = if (it.index == indexWorkSet.index) indexWorkSet.index else it.index,
                weight = if (it.index == indexWorkSet.index) indexWorkSet.weight else it.weight,
                repetition = if (it.index == indexWorkSet.index) indexWorkSet.repetition else it.repetition
            )
        }
    }

    fun addWorkSet(): () -> Unit = {
        indexWorkSetList.value = indexWorkSetList.value.plus(
            if (indexWorkSetList.value.isEmpty()) IndexWorkSet(1, "30", "1") else {
                indexWorkSetList.value.last().let { it.copy(index = it.index + 1) }
            }
        )
    }


    fun removeWorkSet(): (IndexWorkSet) -> Unit = { indexWorkSet ->
        indexWorkSetList.value = indexWorkSetList.value.minus(indexWorkSet)
    }







}


