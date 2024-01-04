package com.gradation.lift.createRoutine.routine.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.keypad.LiftKeypadTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.createRoutine.routine.data.state.KeypadState
import com.gradation.lift.createRoutine.routine.data.state.KeypadWorkSetState
import com.gradation.lift.createRoutine.routine.data.state.WorkSetState
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    keypadWorkSetState: KeypadWorkSetState,
    workSetState: WorkSetState,
    keypadState: KeypadState,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = "루틴 만들기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no3,
            )
            LiftOutlineButton(
                modifier = modifier
                    .height(LiftTheme.space.space32),
                contentPadding = PaddingValues(
                    start = LiftTheme.space.space16,
                    top = 0.dp,
                    end = LiftTheme.space.space16,
                    bottom = 0.dp
                ),
                onClick = workSetState.addWorkSet,
            ) {
                Text(
                    text = "추가",
                    style = LiftTheme.typography.no5,
                    color = LiftTheme.colorScheme.no4,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painterResource(id = LiftIcon.Plus),
                    contentDescription = null,
                )
            }
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space6)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = CenterVertically
            ) {
                Text(
                    modifier = modifier.weight(2f),
                    text = "Set",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Kg",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center,
                    modifier = modifier.weight(3f)
                )
                Text(
                    text = "Reps",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center,
                    modifier = modifier.weight(3f)
                )
                Spacer(
                    modifier = modifier.weight(1f)
                )
            }
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space6)
            ) {
                itemsIndexed(workSetState.workSetList) { index, workSet ->
                    Row(
                        modifier = modifier
                            .background(
                                LiftTheme.colorScheme.no1,
                                RoundedCornerShape(LiftTheme.space.space8)
                            )
                            .padding(vertical = LiftTheme.space.space8)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            modifier = modifier.weight(2f),
                            text = "${workSet.setNumber}",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no2,
                            textAlign = TextAlign.Center,
                        )
                        LiftKeypadTextField(
                            modifier = modifier
                                .weight(3f)
                                .noRippleClickable {
                                    keypadState.updateState(KeypadWorkSetState.Weight)
                                    keypadState.init(index, workSet)
                                },
                            value = workSet.weight,
                            focused = keypadWorkSetState is KeypadWorkSetState.Weight && keypadState.selectedIndex.value == index
                        )
                        LiftKeypadTextField(
                            modifier = modifier
                                .weight(3f)
                                .noRippleClickable {
                                    keypadState.updateState(KeypadWorkSetState.Repetition)
                                    keypadState.init(index, workSet)
                                },
                            value = workSet.repetition,
                            focused = keypadWorkSetState is KeypadWorkSetState.Repetition && keypadState.selectedIndex.value == index
                        )

                        IconButton(
                            onClick = { workSetState.removeWorkSet(workSet) },
                            modifier = modifier
                                .size(LiftTheme.space.space24)
                                .weight(1f)
                        ) {
                            Icon(
                                painter = painterResource(LiftIcon.Trash),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        }
                    }
                }
            }
        }
    }
}

