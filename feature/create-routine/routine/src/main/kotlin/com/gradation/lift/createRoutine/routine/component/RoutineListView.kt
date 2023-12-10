package com.gradation.lift.createRoutine.routine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.gradation.lift.createRoutine.routine.data.state.KeypadState
import com.gradation.lift.createRoutine.routine.data.model.IndexWorkSet
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.keypad.LiftKeypadTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    indexWorkSetList: List<IndexWorkSet>,
    keypadState: KeypadState,
    updateKeypadState: (KeypadState) -> Unit,
    appendWorkSet: () -> Unit,
    removeWorkSet: (IndexWorkSet) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    .height(32.dp),
                contentPadding = PaddingValues(
                    start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
                ),
                onClick = appendWorkSet,
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Set",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(2f)
            )
            Text(
                text = "Kg",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(2f)
            )
            Text(
                text = "Reps",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(2f)
            )
            Spacer(
                modifier = modifier.weight(1f)
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(indexWorkSetList) { index, workSet ->
                Row(
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no1, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "${index + 1}",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .weight(2f)
                            .align(CenterVertically)
                    )
                    LiftKeypadTextField(
                        modifier = modifier
                            .weight(2f)
                            .clickable {
                                updateKeypadState(
                                    KeypadState.Weight(
                                        index = workSet.index,
                                        weight = workSet.weight
                                    )
                                )
                            },
                        value = workSet.weight.value,
                        focused = keypadState is KeypadState.Weight && keypadState.index == workSet.index
                    )
                    LiftKeypadTextField(
                        modifier = modifier
                            .weight(2f)
                            .clickable {
                                updateKeypadState(
                                    KeypadState.Repetition(
                                        index = workSet.index,
                                        repetition = workSet.repetition
                                    )
                                )
                            },
                        value = workSet.repetition.value,
                        focused = keypadState is KeypadState.Repetition && keypadState.index == workSet.index
                    )

                    IconButton(
                        onClick = { removeWorkSet(workSet) },
                        modifier = modifier
                            .size(18.dp)
                            .weight(1f)
                            .align(CenterVertically)
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

