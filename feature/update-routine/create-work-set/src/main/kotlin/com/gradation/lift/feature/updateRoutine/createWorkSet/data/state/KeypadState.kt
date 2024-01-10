package com.gradation.lift.feature.updateRoutine.createWorkSet.data.state

import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.event.KeypadEvent
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.model.WorkSet
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [KeypadState]
 * 키패드에 대한 정보를 나타내는 상태
 * @since 2023-12-08 18:47:06
 */
class KeypadState {

    val keypadWorkSetState: MutableStateFlow<KeypadWorkSetState> =
        MutableStateFlow(KeypadWorkSetState.None)

    val selectedIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val selectedWorkSet: MutableStateFlow<WorkSet> = MutableStateFlow<WorkSet>(WorkSet())

    val init: (Int, WorkSet) -> Unit =
        { index, workSet -> onKeypadEvent(KeypadEvent.Init(index, workSet)) }
    val clear: () -> Unit = { onKeypadEvent(KeypadEvent.Clear) }
    val appendNumber: (String) -> Unit = { onKeypadEvent(KeypadEvent.AppendNumber(it)) }
    val appendPoint: (String) -> Unit = { onKeypadEvent(KeypadEvent.AppendPoint(it)) }
    val plusNumber: (String) -> Unit = { onKeypadEvent(KeypadEvent.PlusNumber(it)) }
    val minusNumber: (String) -> Unit = { onKeypadEvent(KeypadEvent.MinusNumber(it)) }
    val done: () -> Unit = { onKeypadEvent(KeypadEvent.Done) }
    val updateState: (KeypadWorkSetState) -> Unit = { onKeypadEvent(KeypadEvent.UpdateState(it)) }


    private fun onKeypadEvent(keypadEvent: KeypadEvent) {
        when (keypadEvent) {
            is KeypadEvent.AppendNumber -> {
                when (keypadWorkSetState.value) {
                    KeypadWorkSetState.None -> {}
                    KeypadWorkSetState.Repetition ->
                        selectedWorkSet.value =
                            selectedWorkSet.value.copy(repetition = selectedWorkSet.value.repetition + keypadEvent.number)

                    KeypadWorkSetState.Weight -> {
                        selectedWorkSet.value =
                            selectedWorkSet.value.copy(weight = selectedWorkSet.value.weight + keypadEvent.number)
                    }
                }
            }

            is KeypadEvent.AppendPoint -> {
                when (keypadWorkSetState.value) {
                    KeypadWorkSetState.None -> {}
                    KeypadWorkSetState.Repetition -> {}
                    KeypadWorkSetState.Weight -> {
                        selectedWorkSet.value =
                            selectedWorkSet.value.copy(weight = selectedWorkSet.value.weight + keypadEvent.point)
                    }
                }
            }

            KeypadEvent.Clear -> {
                when (keypadWorkSetState.value) {
                    KeypadWorkSetState.None -> {}
                    KeypadWorkSetState.Repetition ->
                        selectedWorkSet.value = selectedWorkSet.value.copy(repetition = "")

                    KeypadWorkSetState.Weight ->
                        selectedWorkSet.value = selectedWorkSet.value.copy(weight = "")

                }
            }

            KeypadEvent.Done -> {
                when (keypadWorkSetState.value) {
                    KeypadWorkSetState.None -> {}
                    KeypadWorkSetState.Repetition ->
                        selectedWorkSet.value = selectedWorkSet.value.copy(
                            repetition =
                            if (decimalNumberValidator(selectedWorkSet.value.repetition))
                                selectedWorkSet.value.repetition else ""
                        )

                    KeypadWorkSetState.Weight ->
                        selectedWorkSet.value = selectedWorkSet.value.copy(
                            weight = if (decimalNumberValidator(selectedWorkSet.value.weight))
                                selectedWorkSet.value.weight else ""
                        )

                }
            }

            is KeypadEvent.MinusNumber -> {
                when (keypadWorkSetState.value) {
                    KeypadWorkSetState.None -> {}
                    KeypadWorkSetState.Repetition ->
                        selectedWorkSet.value =
                            selectedWorkSet.value.copy(repetition = (selectedWorkSet.value.repetition.toInt() - keypadEvent.number.toInt()).toString())

                    KeypadWorkSetState.Weight ->
                        selectedWorkSet.value =
                            selectedWorkSet.value.copy(weight = (selectedWorkSet.value.weight.toFloat() - keypadEvent.number.toFloat()).toString())
                }
            }

            is KeypadEvent.PlusNumber -> {
                when (keypadWorkSetState.value) {
                    KeypadWorkSetState.None -> {}
                    KeypadWorkSetState.Repetition ->
                        selectedWorkSet.value =
                            selectedWorkSet.value.copy(repetition = (selectedWorkSet.value.repetition.toInt() + keypadEvent.number.toInt()).toString())

                    KeypadWorkSetState.Weight ->
                        selectedWorkSet.value =
                            selectedWorkSet.value.copy(weight = (selectedWorkSet.value.weight.toFloat() + keypadEvent.number.toFloat()).toString())
                }
            }

            is KeypadEvent.UpdateState -> {
                keypadWorkSetState.value = keypadEvent.state
            }

            is KeypadEvent.Init -> {
                selectedIndex.value = keypadEvent.index
                selectedWorkSet.value = keypadEvent.workSet
            }
        }
    }
}
