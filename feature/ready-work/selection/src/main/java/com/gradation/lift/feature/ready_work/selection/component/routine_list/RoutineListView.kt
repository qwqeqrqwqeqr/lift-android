package com.gradation.lift.feature.ready_work.selection.component.routine_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.ready_work.selection.ReadyWorkSelectionScreen
import com.gradation.lift.feature.ready_work.selection.data.RoutineSelection
import com.gradation.lift.feature.ready_work.selection.data.RoutineSetRoutineSelection
import com.gradation.lift.feature.ready_work.selection.data.RoutineSetRoutineSelectionUiState
import com.gradation.lift.feature.ready_work.selection.data.WeekdayCard
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.test.data.TestModelDataGenerator


@Composable
fun RoutineSetRoutineListView(
    modifier: Modifier = Modifier,
    routineSetRoutineSelection: List<RoutineSetRoutineSelection>,
    onUpdateRoutineSetRoutineList: (Int, Boolean) -> Unit,
    onUpdateRoutineList: (Int, Boolean) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 20.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(routineSetRoutineSelection) { _, routineSetRoutine ->
            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .border(
                        width = 1.dp,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .clickable(
                            onClick = {
                                onUpdateRoutineSetRoutineList(
                                    routineSetRoutine.id,
                                    !routineSetRoutine.selected
                                )
                            })
                        .padding(
                            start = 16.dp,
                            top = 12.dp,
                            bottom = 12.dp,
                            end = 24.dp
                        )


                ) {
                    ToggleCheckbox(
                        checked = routineSetRoutine.selected,
                        onCheckedChange = {},
                        modifier = modifier.size(26.dp)
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            text = routineSetRoutine.name,
                            style = LiftTheme.typography.no2,
                            color = if (routineSetRoutine.selected) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no9,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = routineSetRoutine.description,
                            style = LiftTheme.typography.no4,
                            color = LiftTheme.colorScheme.no9,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                    Spacer(modifier = modifier.padding(4.dp))
                    Icon(
                        painterResource(id = LiftIcon.ChevronRight),
                        contentDescription = null,
                        modifier = modifier
                            .width(10.dp)
                            .height(16.dp)
                    )
                }
                routineSetRoutine.routine.forEach { routine ->
                    RoutineListView(
                        modifier,
                        routine,
                        onUpdateRoutineList
                    )
                }
                Spacer(modifier = modifier.padding(4.dp))
            }
            Spacer(modifier = modifier.padding(2.dp))
        }
    }
}

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    routine: RoutineSelection,
    onUpdateRoutineList: (Int, Boolean) -> Unit,
) {
    Divider(
        modifier = modifier,
        thickness = 4.dp,
        color = LiftTheme.colorScheme.no1,
    )
    Spacer(modifier = modifier.padding(2.dp))
    Column(
        modifier = modifier
            .clickable(
                onClick = {
                    onUpdateRoutineList(
                        routine.routine.id,
                        !routine.opened
                    )
                }
            )
            .padding(
                start = 16.dp,
                top = 12.dp,
                bottom = 12.dp,
                end = 24.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()

        ) {
            Text(
                text = routine.routine.workCategory.name,
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
            )
            Icon(
                painterResource(id = if (routine.opened) LiftIcon.ChevronDown else LiftIcon.ChevronUp),
                contentDescription = null,
                modifier = modifier
                    .height(12.dp)
            )
        }
        Spacer(modifier = modifier.padding(4.dp))

    }
    if (routine.opened) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
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
            routine.routine.workSetList.forEachIndexed { index, workSet ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = LiftTheme.colorScheme.no1,
                                shape = RoundedCornerShape(
                                    size = 30.dp
                                )
                            )
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = LiftTheme.colorScheme.no1,
                                shape = RoundedCornerShape(
                                    size = 30.dp
                                )
                            )
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${workSet.weight}",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .background(
                                color = LiftTheme.colorScheme.no1,
                                shape = RoundedCornerShape(
                                    size = 30.dp
                                )
                            )
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${workSet.repetition}",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                Spacer(modifier = modifier.padding(4.dp))
            }
        }
    }
}

@Composable
@Preview
fun ReadyWorkSelectionPreview() {
    LiftMaterialTheme {
        ReadyWorkSelectionScreen(
            modifier = Modifier,
            weekday = listOf(
                WeekdayCard(weekday = Weekday.Monday()),
                WeekdayCard(weekday = Weekday.Tuesday()),
                WeekdayCard(weekday = Weekday.Wednesday()),
                WeekdayCard(weekday = Weekday.Thursday()),
                WeekdayCard(weekday = Weekday.Friday()),
                WeekdayCard(weekday = Weekday.Saturday()),
                WeekdayCard(weekday = Weekday.Sunday(), selected = true)
            ),
            routineSetRoutineSelection = RoutineSetRoutineSelectionUiState.Success(
                routineSetRoutineSelection = TestModelDataGenerator.Routine.routineSetRoutineModelList.map {
                    RoutineSetRoutineSelection(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        weekday = it.weekday,
                        selected = false,
                        routine = it.routine.map { routine ->
                            RoutineSelection(
                                routine = routine,
                                opened = true
                            )
                        }
                    )
                }
            ),
            onBackClickTopBar = {},
            onClickWeekDayCard = {},
            onClickStartWork = {},
            onUpdateRoutineSetRoutineList = { _, _ -> },
            onUpdateRoutineList = { _, _ -> },
            selectedRoutine = 2
        )

    }
}
