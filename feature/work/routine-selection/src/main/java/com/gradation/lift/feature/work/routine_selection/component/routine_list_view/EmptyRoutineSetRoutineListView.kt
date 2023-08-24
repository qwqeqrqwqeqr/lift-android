package com.gradation.lift.feature.work.routine_selection.component.routine_list_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.WorkRoutineSelectionScreen
import com.gradation.lift.feature.work.routine_selection.component.NavigationView
import com.gradation.lift.feature.work.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.feature.work.routine_selection.data.state.RoutineSetRoutineSelectionUiState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.utils.ModelDataGenerator

@Composable
fun EmptyRoutineSetRoutineListView(
    modifier: Modifier = Modifier,
    selectedRoutineCount: Int,
    selectedRoutineSetList: List<RoutineSetRoutine>,
    updateRoutineSetRoutineList: (List<RoutineSetRoutine>) -> Unit,
    navigateSelectionRoutineToWork: () -> Unit,
) {
    Surface(modifier = modifier
        .fillMaxSize()
        .background(LiftTheme.colorScheme.no5)) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(16.dp)
            ) {

            Box(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.open_box),
                        contentDescription = "",
                        modifier = modifier
                            .size(96.dp)
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "루틴이 존재하지 않네요...",
                        style = LiftTheme.typography.no4,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = modifier.padding(8.dp))
                }
            }
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
@Preview
fun WorkRoutineSelectionPreview() {
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
            routineSetRoutineSelectionUiState = RoutineSetRoutineSelectionUiState.Empty,
            navigateWorkGraphToHomeGraph = { },
            updateCurrentDate = { },
            navigateSelectionRoutineToWork = {},
            updateRoutineSetRoutineList = { },
            updateSelectedRoutineSetList = { _, _ -> },
            updateOpenedRoutineIdList = { _, _ -> }
        )
    }
}