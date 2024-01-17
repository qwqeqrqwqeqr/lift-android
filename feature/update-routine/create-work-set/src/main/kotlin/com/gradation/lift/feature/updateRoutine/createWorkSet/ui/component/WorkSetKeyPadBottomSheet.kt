package com.gradation.lift.feature.updateRoutine.createWorkSet.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.keypad.RepetitionKeypadContainer
import com.gradation.lift.designsystem.component.keypad.WeightKeypadContainer
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.KeypadState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.KeypadWorkSetState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.WorkSetState

@Composable
fun WorkSetKeyPadBottomSheet(
    modifier: Modifier = Modifier,
    keypadWorkSetState: KeypadWorkSetState,
    workSetState: WorkSetState,
    keypadState: KeypadState,
) {
    when (keypadWorkSetState) {
        KeypadWorkSetState.None -> {}
        KeypadWorkSetState.Repetition -> {
            RepetitionKeypadContainer(
                modifier = modifier.fillMaxWidth(),
                clearNumber = {
                    keypadState.clear()
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                },
                appendNumber = {
                    keypadState.appendNumber(it)
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                },
                plusNumber = {
                    keypadState.plusNumber(it)
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )

                },
                minusNumber = {
                    keypadState.minusNumber(it)
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                },
                onDismissRequest = {
                    keypadState.done()
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                    keypadState.updateState(KeypadWorkSetState.None)
                }
            )
        }

        KeypadWorkSetState.Weight -> {
            WeightKeypadContainer(
                modifier = modifier.fillMaxWidth(),
                clearNumber = {
                    keypadState.clear()
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                },
                appendNumber = {
                    keypadState.appendNumber(it)
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                },
                appendPoint = {
                    keypadState.appendPoint(it)
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )

                },
                plusNumber = {
                    keypadState.plusNumber(it)
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                },
                minusNumber = {
                    keypadState.minusNumber(it)
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                },
                onDismissRequest = {
                    keypadState.done()
                    workSetState.updateWorkSet(
                        keypadState.selectedIndex.value,
                        keypadState.selectedWorkSet.value
                    )
                    keypadState.updateState(KeypadWorkSetState.None)
                }
            )
        }
    }

}