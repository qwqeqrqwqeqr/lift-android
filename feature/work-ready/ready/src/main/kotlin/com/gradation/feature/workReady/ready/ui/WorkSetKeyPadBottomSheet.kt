package com.gradation.feature.workReady.ready.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.keypad.RepetitionKeypadContainer
import com.gradation.lift.designsystem.component.keypad.WeightKeypadContainer
import com.gradation.lift.feature.workReady.common.WorkRoutineState
import com.gradation.lift.feature.workReady.common.state.KeypadState
import com.gradation.lift.feature.workReady.common.state.KeypadWorkSetState

@Composable
fun WorkSetKeyPadBottomSheet(
    modifier: Modifier = Modifier,
    keypadWorkSetState: KeypadWorkSetState,
    workRoutineState: WorkRoutineState,
    keypadState: KeypadState,
) {
    when (keypadWorkSetState) {
        KeypadWorkSetState.None -> {}
        KeypadWorkSetState.Repetition -> {
            RepetitionKeypadContainer(
                modifier = modifier.fillMaxWidth(),
                clearNumber = {
                    keypadState.clear()
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                appendNumber = {
                    keypadState.appendNumber(it)
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                plusNumber = {
                    keypadState.plusNumber(it)
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }

                },
                minusNumber = {
                    keypadState.minusNumber(it)
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                onDismissRequest = {
                    keypadState.done()
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                    keypadState.updateState(KeypadWorkSetState.None)
                }
            )
        }

        KeypadWorkSetState.Weight -> {
            WeightKeypadContainer(
                modifier = modifier.fillMaxWidth(),
                clearNumber = {
                    keypadState.clear()
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                appendNumber = {
                    keypadState.appendNumber(it)
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                appendPoint = {
                    keypadState.appendPoint(it)
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                plusNumber = {
                    keypadState.plusNumber(it)
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                minusNumber = {
                    keypadState.minusNumber(it)
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                },
                onDismissRequest = {
                    keypadState.done()
                    keypadState.selectedRoutineIndex.value?.let { routineIndex->
                        workRoutineState.updateWorkSet(
                            routineIndex,
                            keypadState.selectedWorkSetIndex.value,
                            keypadState.selectedWorkSet.value
                        )
                    }
                    keypadState.updateState(KeypadWorkSetState.None)
                }
            )
        }
    }

}