package com.gradation.lift.feature.work.routine_selection

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.component.WeekdayCardListView
import com.gradation.lift.feature.work.routine_selection.component.routine_list.EmptyRoutineListView
import com.gradation.lift.feature.work.routine_selection.component.routine_list.LoadingRoutineSetRoutineListView
import com.gradation.lift.feature.work.routine_selection.component.routine_list.RoutineSetRoutineListView
import com.gradation.lift.feature.work.routine_selection.data.*
import com.gradation.lift.feature.work.routine_selection.data.WeekdayCard
import com.gradation.lift.model.common.Weekday
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun WorkRoutineSelectionRoute(
    navController: NavController,
    navigateToReadyWorkChangeOrder : () -> Unit,
    navigateReadyWorkToMain  : () -> Unit,
    previousRoutineSetId: Int?,
    modifier: Modifier = Modifier,
    viewModel: WorkRoutineSelectionViewModel = hiltViewModel(),
) {

    val weekDate: List<WeekdayCard> by viewModel.weekDate.collectAsStateWithLifecycle()
    val routineSetRoutineSelection: RoutineSetRoutineSelectionUiState by viewModel.routineSetRoutineSelection.collectAsStateWithLifecycle()
    val selectedRoutine by viewModel.selectedRoutine.collectAsStateWithLifecycle()


    WorkRoutineSelectionScreen(
        modifier = modifier,
        weekday = weekDate,
        routineSetRoutineSelection = routineSetRoutineSelection,
        onBackClickTopBar = navigateReadyWorkToMain,
        onClickWeekDayCard = viewModel.updateCurrentDate(),
        onClickStartWork = {
            viewModel.updateKey(navController)
            navigateToReadyWorkChangeOrder()
        },
        selectedRoutine = selectedRoutine,
        onUpdateRoutineSetRoutineList = viewModel.updateSelectedRoutineSetIdList(),
        onUpdateRoutineList = viewModel.updateOpenedRoutineIdList(),
    )

    LaunchedEffect(key1 = true) {
        viewModel.updatePreviousRoutineSetId(previousRoutineSetId)
    }
    BackHandler(enabled = true, onBack = navigateReadyWorkToMain)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WorkRoutineSelectionScreen(
    modifier: Modifier = Modifier,
    weekday: List<WeekdayCard>,
    routineSetRoutineSelection: RoutineSetRoutineSelectionUiState,
    onBackClickTopBar: () -> Unit,
    onClickWeekDayCard: (LocalDate) -> Unit,
    onClickStartWork: () -> Unit,
    onUpdateRoutineSetRoutineList: (Int, Boolean) -> Unit,
    onUpdateRoutineList: (Int, Boolean) -> Unit,
    selectedRoutine: Int,
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 선택",
                onBackClickTopBar = onBackClickTopBar
            )
        },
        floatingActionButton = {
            LiftButton(
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = onClickStartWork,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    ),
                enabled = selectedRoutine != 0
            ) {
                Text(
                    text = "운동시작하기 (${selectedRoutine}개)",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight()
                        .width(8.dp)
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            WeekdayCardListView(
                weekday = weekday,
                modifier = modifier,
                onClickWeekDayCard = onClickWeekDayCard
            )

            when (routineSetRoutineSelection) {
                RoutineSetRoutineSelectionUiState.Empty -> {
                    EmptyRoutineListView(modifier=modifier)
                }
                is RoutineSetRoutineSelectionUiState.Fail -> {


                }
                RoutineSetRoutineSelectionUiState.Loading -> {
                    LoadingRoutineSetRoutineListView(modifier = modifier)
                }
                is RoutineSetRoutineSelectionUiState.Success -> {
                    RoutineSetRoutineListView(
                        modifier = modifier,
                        routineSetRoutineSelection = routineSetRoutineSelection.routineSetRoutineSelection,
                        onUpdateRoutineSetRoutineList = onUpdateRoutineSetRoutineList,
                        onUpdateRoutineList = onUpdateRoutineList
                    )

                }
            }
        }
    }
}


@Composable
@Preview
fun WorkRoutineSelectionPreview() {
    LiftMaterialTheme {
        WorkRoutineSelectionScreen(
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
            routineSetRoutineSelection = RoutineSetRoutineSelectionUiState.Empty,
            onBackClickTopBar = {},
            onClickWeekDayCard = {},
            onClickStartWork = {},
            onUpdateRoutineSetRoutineList = { _, _ -> },
            onUpdateRoutineList = { _, _ -> },
            selectedRoutine = 2
        )
    }
}