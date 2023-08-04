package com.gradation.lift.feature.create_routine.routine_set.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.routine.CreateRoutine

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    routine: State<List<CreateRoutine>>,
    onAddRoutine: () -> Unit,
    onRemoveRoutineSet: (CreateRoutine) -> Unit
) {
    if (routine.value.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = LiftTheme.colorScheme.no1,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = modifier
                        .background(
                            LiftTheme.colorScheme.no4,
                            shape = RoundedCornerShape(size = 64.dp)
                        )
                        .size(42.dp), contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = onAddRoutine) {
                        Icon(
                            modifier = modifier.size(16.dp),
                            painter = painterResource(id = LiftIcon.Plus),
                            contentDescription = "",
                            tint = LiftTheme.colorScheme.no5
                        )
                    }
                }
                Spacer(modifier = modifier.padding(5.dp))
                Text(
                    text = "+ 버튼을 눌러 루틴을 추가해요",
                    style = LiftTheme.typography.no6,
                    color = LiftTheme.colorScheme.no2
                )

            }
        }
    } else {
        routine.value.forEachIndexed { _, createRoutine ->
            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .border(
                        width = 1.dp,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(14.dp)
                    .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = createRoutine.workCategory,
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        modifier = modifier.weight(1f)
                    )
                    Row(
                        modifier = modifier.weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(LiftIcon.Trash),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = modifier.clickable(
                                onClick = { onRemoveRoutineSet(createRoutine) }
                            )
                        )
                        Spacer(modifier = modifier.padding(12.dp))
                        Icon(
                            painter = painterResource(LiftIcon.Order),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = modifier
                        )
                    }
                }
                Spacer(
                    modifier = modifier.padding(8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Set",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier.weight(1f)
                    )
                    Text(
                        text = "Kg",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier.weight(1f)
                    )
                    Text(
                        text = "Reps",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier.weight(1f)
                    )
                }
                Spacer(modifier = modifier.padding(4.dp))
                createRoutine.workSetList.forEachIndexed { index, workSet ->
                    Row(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .border(
                                width = 1.dp,
                                color = LiftTheme.colorScheme.no8,
                                shape = RoundedCornerShape(size = 8.dp)
                            )
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
                                .weight(1f)
                                .padding(3.dp)
                        )

                        Text(
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9,
                            text = workSet.weight.toString(),
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .background(
                                    color = LiftTheme.colorScheme.no1,
                                    shape = RoundedCornerShape(size = 6.dp)
                                )
                                .padding(3.dp)

                        )

                        Text(
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9,
                            text = workSet.repetition.toString(),
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .background(
                                    color = LiftTheme.colorScheme.no1,
                                    shape = RoundedCornerShape(size = 6.dp)
                                )
                                .padding(3.dp)

                        )
                    }
                    Spacer(modifier = modifier.padding(2.dp))

                }
            }

        }
    }
}