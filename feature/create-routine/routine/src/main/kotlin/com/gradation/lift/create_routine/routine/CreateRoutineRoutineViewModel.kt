package com.gradation.lift.create_routine.routine

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.create_routine.routine.data.state.KeypadState
import com.gradation.lift.create_routine.routine.data.model.IndexWorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [CreateRoutineRoutineViewModel]
 * @property indexWorkSetList 운동 세트 리스트
 * @property keypadState 키패드에 대한 상태
 * @property createRoutineCondition 루틴생선 조건 해당 필드를 뷰와 연결시켜 제어함
 * @since 2023-10-12 11:05:29
 */
@HiltViewModel
class CreateRoutineRoutineViewModel @Inject constructor(
) : ViewModel() {


    val indexWorkSetList: MutableStateFlow<List<IndexWorkSet>> =
        MutableStateFlow(emptyList())


    val keypadState: MutableStateFlow<KeypadState> = MutableStateFlow(KeypadState.None)


    val createRoutineCondition: StateFlow<Boolean> =
        indexWorkSetList.map { it.isNotEmpty() }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )


    fun appendWorkSet(): () -> Unit = {
        indexWorkSetList.value = indexWorkSetList.value.plus(
            if (indexWorkSetList.value.isEmpty()) IndexWorkSet(
                1,
                mutableStateOf("30"),
                mutableStateOf("10")
            )
            else {
                indexWorkSetList.value.last().let { indexWorkSet ->
                    IndexWorkSet(
                        index = indexWorkSet.index + 1,
                        weight = mutableStateOf(indexWorkSet.weight.value),
                        repetition = mutableStateOf(indexWorkSet.repetition.value)
                    )
                }
            }
        )
    }


    fun updateWeight(): (Int, String) -> Unit = { index, weight ->
        indexWorkSetList.update {
            it.apply {
                this[index - 1].weight.value = weight
            }
        }
    }

    fun updateRepetition(): (Int, String) -> Unit = { index, repetition ->
        indexWorkSetList.update {
            it.apply {
                this[index - 1].repetition.value = repetition
            }
        }
    }


    fun removeWorkSet(): (IndexWorkSet) -> Unit = { indexWorkSet ->
        indexWorkSetList.update {
            it.minus(indexWorkSet)
        }
    }


    fun updateKeypadState(): (KeypadState) -> Unit = {
        keypadState.value = it
    }


}


