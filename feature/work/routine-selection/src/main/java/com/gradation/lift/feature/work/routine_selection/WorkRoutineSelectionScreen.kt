package com.gradation.lift.feature.work.routine_selection

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.component.routine_list_view.EmptyRoutineSetRoutineListView
import com.gradation.lift.feature.work.routine_selection.component.routine_list_view.LoadingRoutineSetRoutineListView
import com.gradation.lift.feature.work.routine_selection.component.routine_list_view.RoutineSetRoutineListView
import com.gradation.lift.feature.work.routine_selection.component.week_date_card_view.WeekDateCardListView
import com.gradation.lift.feature.work.routine_selection.data.*
import com.gradation.lift.feature.work.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.feature.work.routine_selection.data.state.RoutineSetRoutineSelectionUiState
import com.gradation.lift.feature.work.work.data.model.RoutineSelection
import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList
import com.gradation.lift.navigation.Router
import kotlinx.datetime.LocalDate

@SuppressLint("UnrememberedGetBackStackEntry")

@Composable
internal fun WorkRoutineSelectionRoute(
    navController: NavController,
    navigateSelectionRoutineToWorkInWorkGraph: () -> Unit,
    navigateWorkGraphToHomeGraph: () -> Unit,
    selectedRoutineSetId: Int?,
    modifier: Modifier = Modifier,
    viewModel: WorkRoutineSelectionViewModel = hiltViewModel(),
) {
    val workBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)

    val selectedRoutineSetList: List<RoutineSetRoutine> by viewModel.selectedRoutineSetState.selectedRoutineSetList.collectAsStateWithLifecycle()
    val selectedRoutineCount: Int by viewModel.selectedRoutineSetState.selectedRoutineCount.collectAsStateWithLifecycle()
    val updateSelectedRoutineSetList: (RoutineSetRoutine, Boolean) -> Unit =
        viewModel.selectedRoutineSetState.updateSelectedRoutineSetList()
    val appendBySelectedRoutineSetRoutineId: (Int?) -> Unit =
        viewModel.selectedRoutineSetState.appendBySelectedRoutineSetRoutineId()

    val updateOpenedRoutineIdList: (Int, Boolean) -> Unit =
        viewModel.openedRoutineState.updateOpenedRoutineIdList()


    val weekDate: List<WeekDateSelection> by viewModel.dateState.weekDate.collectAsStateWithLifecycle()
    val updateCurrentDate: (LocalDate) -> Unit = viewModel.dateState.updateCurrentDate()

    val routineSetRoutineSelectionUiState: RoutineSetRoutineSelectionUiState by viewModel.routineSetRoutineState.collectAsStateWithLifecycle()


    val updateRoutineSetRoutineList: (List<RoutineSetRoutine>) -> Unit =
        sharedViewModel.updateRoutineSetRoutineList()


    WorkRoutineSelectionScreen(
        modifier,
        weekDate,
        selectedRoutineSetList,
        selectedRoutineCount,
        routineSetRoutineSelectionUiState,
        updateCurrentDate,
        updateRoutineSetRoutineList,
        updateSelectedRoutineSetList,
        updateOpenedRoutineIdList,
        navigateWorkGraphToHomeGraph,
        navigateSelectionRoutineToWorkInWorkGraph
    )

    LaunchedEffect(key1 = true) {
        appendBySelectedRoutineSetRoutineId(selectedRoutineSetId)
    }
    BackHandler(enabled = true, onBack = navigateWorkGraphToHomeGraph)


}

@Composable
internal fun WorkRoutineSelectionScreen(
    modifier: Modifier = Modifier,
    weekDate: List<WeekDateSelection>,
    selectedRoutineSetList: List<RoutineSetRoutine>,
    selectedRoutineCount: Int,
    routineSetRoutineSelectionUiState: RoutineSetRoutineSelectionUiState,
    updateCurrentDate: (LocalDate) -> Unit,
    updateRoutineSetRoutineList: (List<RoutineSetRoutine>) -> Unit,
    updateSelectedRoutineSetList: (RoutineSetRoutine, Boolean) -> Unit,
    updateOpenedRoutineIdList: (Int, Boolean) -> Unit,
    navigateWorkGraphToHomeGraph: () -> Unit,
    navigateSelectionRoutineToWorkInWorkGraph: () -> Unit,
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴 선택",
                onBackClickTopBar = navigateWorkGraphToHomeGraph
            )
        }
    ) {
        Surface(
            color = LiftTheme.colorScheme.no17,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = modifier
            ) {
                WeekDateCardListView(
                    modifier, weekDate, updateCurrentDate
                )

                when (routineSetRoutineSelectionUiState) {
                    RoutineSetRoutineSelectionUiState.Empty -> {
                        EmptyRoutineSetRoutineListView(
                            modifier,
                            selectedRoutineCount,
                            selectedRoutineSetList,
                            updateRoutineSetRoutineList,
                            navigateSelectionRoutineToWorkInWorkGraph
                        )
                    }
                    is RoutineSetRoutineSelectionUiState.Fail -> {

                    }
                    RoutineSetRoutineSelectionUiState.Loading -> {
                        LoadingRoutineSetRoutineListView(modifier = modifier.padding(horizontal = 16.dp))
                    }
                    is RoutineSetRoutineSelectionUiState.Success -> {
                        RoutineSetRoutineListView(
                            modifier = modifier,
                            selectedRoutineSetList = selectedRoutineSetList,
                            selectedRoutineCount = selectedRoutineCount,
                            routineSetRoutineSelection = routineSetRoutineSelectionUiState.routineSetRoutineSelection,
                            updateRoutineSetRoutineList = updateRoutineSetRoutineList,
                            updateSelectedRoutineSetList = updateSelectedRoutineSetList,
                            updateOpenedRoutineIdList = updateOpenedRoutineIdList,
                            navigateSelectionRoutineToWorkInWorkGraph = navigateSelectionRoutineToWorkInWorkGraph
                        )

                    }
                }
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
            selectedRoutineSetList = routineSetRoutineModelList,
            selectedRoutineCount = 3,
            routineSetRoutineSelectionUiState = RoutineSetRoutineSelectionUiState.Success(
                routineSetRoutineSelection = routineSetRoutineModelList.map {
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
            navigateSelectionRoutineToWorkInWorkGraph = {},
            updateRoutineSetRoutineList = { },
            updateSelectedRoutineSetList = { _, _ -> },
            updateOpenedRoutineIdList = { _, _ -> }
        )
    }
}