package com.gradation.lift.feature.work.routine_selection.component.routine_list_view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.WorkRoutineSelectionScreen
import com.gradation.lift.feature.work.routine_selection.component.NavigationView
import com.gradation.lift.feature.work.routine_selection.component.routine_view.RoutineListView
import com.gradation.lift.feature.work.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.feature.work.routine_selection.data.state.RoutineSetRoutineSelectionUiState
import com.gradation.lift.feature.work.work.data.model.RoutineSelection
import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.utils.ModelDataGenerator


@Composable
fun RoutineSetRoutineListView(
    modifier: Modifier = Modifier,
    selectedRoutineSetList: List<RoutineSetRoutine>,
    selectedRoutineCount: Int,
    routineSetRoutineSelection: List<RoutineSetRoutineSelection>,
    updateRoutineSetRoutineList: (List<RoutineSetRoutine>) -> Unit,
    updateSelectedRoutineSetList: (RoutineSetRoutine, Boolean) -> Unit,
    updateOpenedRoutineIdList: (Int, Boolean) -> Unit,
    navigateSelectionRoutineToWorkInWorkGraph: () -> Unit,
) {
    Surface(color = LiftTheme.colorScheme.no17) {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(routineSetRoutineSelection) { _, routineSetRoutine ->
                Column(
                    modifier = modifier
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .clip(RoundedCornerShape(size = 12.dp))
                        .background(LiftTheme.colorScheme.no5)
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
                                        picture = "",
                                        weekday = emptySet(),
                                        label = emptySet(),
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
                                        weekday = emptySet(),
                                        label = emptySet(),
                                        picture = "",
                                        routine = routineSetRoutine.routine.map { it.routine }),
                                    it
                                )
                            },
                            modifier = modifier.size(26.dp)
                        )
                        Spacer(modifier = modifier.padding(8.dp))
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
                    navigateSelectionRoutineToWorkInWorkGraph
                )
            }

        }
    }
}


@Composable
@Preview
fun WorkRoutineSelectionScreenPreview() {
    LiftMaterialTheme {
        WorkRoutineSelectionScreen(
            weekDate = listOf(
                WeekDateSelection(weekday = Weekday.Monday()),
                WeekDateSelection(weekday = Weekday.Tuesday()),
                WeekDateSelection(weekday = Weekday.Wednesday()),
                WeekDateSelection(weekday = Weekday.Thursday()),
                WeekDateSelection(weekday = Weekday.Friday()),
                WeekDateSelection(weekday = Weekday.Saturday()),
                WeekDateSelection(weekday = Weekday.Sunday(), selected = true)
            ),
            selectedRoutineSetList = ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList,
            selectedRoutineCount = 3,
            routineSetRoutineSelectionUiState = RoutineSetRoutineSelectionUiState.Success(
                routineSetRoutineSelection = ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList.map {
                    RoutineSetRoutineSelection(
                        id = it.id,
                        name = it.name,
                        description = it.description,
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
            navigateWorkGraphToHomeGraph = { },
            updateCurrentDate = { },
            navigateSelectionRoutineToWorkInWorkGraph = {},
            updateRoutineSetRoutineList = { },
            updateSelectedRoutineSetList = { _, _ -> },
            updateOpenedRoutineIdList = { _, _ -> }
        )
    }
}