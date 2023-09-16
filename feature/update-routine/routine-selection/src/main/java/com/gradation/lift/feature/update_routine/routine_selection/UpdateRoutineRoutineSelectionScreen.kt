package com.gradation.lift.feature.update_routine.routine_selection

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.gradation.lift.feature.update_routine.routine_selection.component.routine_list_view.EmptyRoutineSetRoutineListView
import com.gradation.lift.feature.update_routine.routine_selection.component.routine_list_view.LoadingRoutineSetRoutineListView
import com.gradation.lift.feature.update_routine.routine_selection.component.routine_list_view.RoutineSetRoutineListView
import com.gradation.lift.feature.update_routine.routine_selection.component.week_date_card_view.WeekDateCardListView
import com.gradation.lift.feature.update_routine.routine_selection.data.viewmodel.UpdateRoutineRoutineSelectionViewModel
import com.gradation.lift.feature.update_routine.routine_selection.data.viewmodel.UpdateRoutineSharedViewModel
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSelection
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSetRoutineSelection
import com.gradation.lift.feature.update_routine.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.feature.update_routine.routine_selection.data.state.RoutineSetRoutineSelectionUiState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.navigation.Router
import kotlinx.datetime.LocalDate

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun UpdateRoutineRoutineSelectionRoute(
    navController: NavController,
    navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph: () -> Unit,
    navigateUpdateRoutineGraphToHomeGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineRoutineSelectionViewModel = hiltViewModel(),
) {
    val workBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.UPDATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(workBackStackEntry)


    val weekDate: List<WeekDateSelection> by viewModel.dateState.weekDate.collectAsStateWithLifecycle()
    val routineSetRoutineSelectionUiState: RoutineSetRoutineSelectionUiState by viewModel.routineSetRoutineState.collectAsStateWithLifecycle()


    val updateOpenedRoutineIdList: (Int, Boolean) -> Unit =
        viewModel.openedRoutineState.updateOpenedRoutineIdList()

    val updateCurrentDate: (LocalDate) -> Unit = viewModel.dateState.updateCurrentDate()
    val updateSelectedRoutine: (RoutineSetRoutineSelection) -> Unit =
        sharedViewModel.updateSelectedRoutineSetRoutineWithRoutineSelection()



    BackHandler(enabled = true, onBack = navigateUpdateRoutineGraphToHomeGraph)


    UpdateRoutineRoutineSelectionScreen(
        modifier,
        weekDate,
        routineSetRoutineSelectionUiState,
        updateOpenedRoutineIdList,
        updateCurrentDate,
        updateSelectedRoutine,
        navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph,
        navigateUpdateRoutineGraphToHomeGraph
    )

}

@Composable
internal fun UpdateRoutineRoutineSelectionScreen(
    modifier: Modifier = Modifier,
    weekDate: List<WeekDateSelection>,
    routineSetRoutineSelectionUiState: RoutineSetRoutineSelectionUiState,
    updateOpenedRoutineIdList: (Int, Boolean) -> Unit,
    updateCurrentDate: (LocalDate) -> Unit,
    updateSelectedRoutine: (RoutineSetRoutineSelection) -> Unit,
    navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph: () -> Unit,
    navigateUpdateRoutineGraphToHomeGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 수정",
                onBackClickTopBar = navigateUpdateRoutineGraphToHomeGraph
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
                        EmptyRoutineSetRoutineListView(modifier)
                    }

                    is RoutineSetRoutineSelectionUiState.Fail -> {

                    }

                    RoutineSetRoutineSelectionUiState.Loading -> {
                        LoadingRoutineSetRoutineListView(modifier = modifier.padding(horizontal = 16.dp))
                    }

                    is RoutineSetRoutineSelectionUiState.Success -> {
                        RoutineSetRoutineListView(
                            modifier = modifier,
                            routineSetRoutineSelection = routineSetRoutineSelectionUiState.routineSetRoutineSelection,
                            updateOpenedRoutineIdList = updateOpenedRoutineIdList,
                            updateSelectedRoutine = updateSelectedRoutine,
                            navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph = navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun UpdateRoutineRoutineSelectionScreenPreview() {
    LiftMaterialTheme {
        UpdateRoutineRoutineSelectionScreen(
            weekDate = listOf(
                WeekDateSelection(weekday = Weekday.Monday()),
                WeekDateSelection(weekday = Weekday.Tuesday()),
                WeekDateSelection(weekday = Weekday.Wednesday()),
                WeekDateSelection(weekday = Weekday.Thursday()),
                WeekDateSelection(weekday = Weekday.Friday()),
                WeekDateSelection(weekday = Weekday.Saturday()),
                WeekDateSelection(weekday = Weekday.Sunday(), selected = true)
            ),
            routineSetRoutineSelectionUiState = RoutineSetRoutineSelectionUiState.Success(
                routineSetRoutineSelection = ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList.map {
                    RoutineSetRoutineSelection(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        weekday = it.weekday,
                        picture = it.picture,
                        routine = it.routine.map { routine ->
                            RoutineSelection(
                                routine = routine,
                                opened = true
                            )
                        }
                    )
                }
            ),
            updateOpenedRoutineIdList = { _, _ -> },
            updateCurrentDate = { },
            updateSelectedRoutine = {},
            navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph = { },
            navigateUpdateRoutineGraphToHomeGraph = { },
        )
    }
}