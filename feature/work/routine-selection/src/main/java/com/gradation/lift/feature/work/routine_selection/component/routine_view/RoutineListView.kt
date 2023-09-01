package com.gradation.lift.feature.work.routine_selection.component.routine_view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.WorkRoutineSelectionScreen
import com.gradation.lift.feature.work.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.feature.work.routine_selection.data.state.RoutineSetRoutineSelectionUiState
import com.gradation.lift.feature.work.work.data.model.RoutineSelection
import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.utils.ModelDataGenerator


@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    routine: RoutineSelection,
    updateOpenedRoutineIdList: (Int, Boolean) -> Unit,
) {
    Divider(
        modifier = modifier,
        thickness = 2.dp,
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
                painterResource(id = if (routine.opened) LiftIcon.ChevronDown else LiftIcon.ChevronUp),
                contentDescription = null,
                modifier = modifier
                    .height(12.dp)
            )
        }
        Spacer(modifier = modifier.padding(4.dp))

    }
    if (routine.opened) {
        RoutineDetailView(
            modifier = modifier,
            routine = routine.routine
        )
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
            navigateWorkGraphToHomeGraph = { },
            updateCurrentDate = { },
            navigateSelectionRoutineToWork = {},
            updateRoutineSetRoutineList = { },
            updateSelectedRoutineSetList = { _, _ -> },
            updateOpenedRoutineIdList = { _, _ -> }
        )
    }
}