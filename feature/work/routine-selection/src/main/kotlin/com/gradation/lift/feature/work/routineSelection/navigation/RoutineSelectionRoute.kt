package com.gradation.lift.feature.work.routineSelection.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.work.routineSelection.data.viewmodel.RoutineSelectionViewModel
import com.gradation.lift.feature.work.routineSelection.data.model.LabelFilterType
import com.gradation.lift.feature.work.routineSelection.data.model.SortType
import com.gradation.lift.feature.work.routineSelection.data.model.WeekdayFilterType
import com.gradation.lift.feature.work.routineSelection.data.state.RoutineListUiState
import com.gradation.lift.feature.work.routineSelection.data.state.RoutineListInfoState
import com.gradation.lift.feature.work.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.work.routineSelection.data.state.SortFilterState
import com.gradation.lift.feature.work.routineSelection.data.state.rememberRoutineListScreen
import com.gradation.lift.feature.work.routineSelection.ui.RoutineSelectionScreen

@Composable
internal fun RoutineSelectionRoute(
    modifier: Modifier = Modifier,
    navigateWorkGraphToHomeGraph: () -> Unit,
    navigateRoutineSelectionToWorkInWorkGraph: (IntArray) -> Unit,
    viewModel: RoutineSelectionViewModel = hiltViewModel(),
    routineListScreenState: RoutineListScreenState = rememberRoutineListScreen(),
) {

    val sortFilterState: SortFilterState = viewModel.sortFilterState
    val routineListInfoState: RoutineListInfoState = viewModel.routineListInfoState
    val routineSetRoutineList: RoutineListUiState by viewModel.routineSetRoutineList.collectAsStateWithLifecycle()


    val labelFilterType: LabelFilterType by sortFilterState.labelFilterType.collectAsStateWithLifecycle()
    val weekdayFilterType: WeekdayFilterType by sortFilterState.weekdayFilterType.collectAsStateWithLifecycle()
    val searchFilterText: String by sortFilterState.searchFilterText.collectAsStateWithLifecycle()
    val sortType: SortType by sortFilterState.sortType.collectAsStateWithLifecycle()


    BackHandler(onBack = navigateWorkGraphToHomeGraph)

    RoutineSelectionScreen(
        modifier,
        routineListScreenState,
        routineListInfoState,
        routineSetRoutineList,
        sortFilterState,
        labelFilterType,
        weekdayFilterType,
        searchFilterText,
        sortType,
        navigateWorkGraphToHomeGraph,
        navigateRoutineSelectionToWorkInWorkGraph
    )

}