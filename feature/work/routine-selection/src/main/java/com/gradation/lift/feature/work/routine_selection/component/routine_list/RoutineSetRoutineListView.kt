package com.gradation.lift.feature.work.routine_selection.component.routine_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.component.NavigationView
import com.gradation.lift.feature.work.work.data.model.RoutineSelection
import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.utils.toText


@Composable
fun RoutineSetRoutineListView(
    modifier: Modifier = Modifier,
    selectedRoutineSetList: List<RoutineSetRoutine>,
    selectedRoutineCount: Int,
    routineSetRoutineSelection: List<RoutineSetRoutineSelection>,
    updateRoutineSetRoutineList: (List<RoutineSetRoutine>) -> Unit,
    updateSelectedRoutineSetList: (RoutineSetRoutine, Boolean) -> Unit,
    updateOpenedRoutineIdList: (Int, Boolean) -> Unit,
    navigateSelectionRoutineToWork: () -> Unit,
) {
    LazyColumn(
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
                        .noRippleClickable {
                            updateSelectedRoutineSetList(
                                RoutineSetRoutine(
                                    id = routineSetRoutine.id,
                                    name = routineSetRoutine.name,
                                    description = routineSetRoutine.description,
                                    weekday = routineSetRoutine.weekday,
                                    picture = "",
                                    routine = routineSetRoutine.routine.map { it.routine }),
                                !routineSetRoutine.selected
                            )
                        }
                        .padding(
                            start = 16.dp,
                            top = 12.dp,
                            bottom = 12.dp,
                            end = 24.dp
                        )


                ) {
                    ToggleCheckbox(
                        checked = routineSetRoutine.selected,
                        onCheckedChange = {
                            updateSelectedRoutineSetList(
                                RoutineSetRoutine(
                                    id = routineSetRoutine.id,
                                    name = routineSetRoutine.name,
                                    description = routineSetRoutine.description,
                                    weekday = routineSetRoutine.weekday,
                                    picture = "",
                                    routine = routineSetRoutine.routine.map { it.routine }),
                                it
                            )
                        },
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
                        updateOpenedRoutineIdList
                    )
                }
                Spacer(modifier = modifier.padding(4.dp))
            }
            Spacer(modifier = modifier.padding(2.dp))
        }
        item {
            NavigationView(
                modifier,
                selectedRoutineCount,
                selectedRoutineSetList,
                updateRoutineSetRoutineList,
                navigateSelectionRoutineToWork
            )
        }

    }
}

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    routine: RoutineSelection,
    updateOpenedRoutineIdList: (Int, Boolean) -> Unit,
) {
    Divider(
        modifier = modifier,
        thickness = 4.dp,
        color = LiftTheme.colorScheme.no1,
    )
    Spacer(modifier = modifier.padding(2.dp))
    Column(
        modifier = modifier
            .noRippleClickable {

                updateOpenedRoutineIdList(
                    routine.routine.id,
                    !routine.opened
                )

            }
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
                painterResource(id = if (routine.opened) LiftIcon.ChevronUp else LiftIcon.ChevronDown),
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
                            text = workSet.weight.toText(),
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