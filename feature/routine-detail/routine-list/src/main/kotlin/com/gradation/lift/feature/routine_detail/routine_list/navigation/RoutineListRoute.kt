package com.gradation.lift.feature.routine_detail.routine_list.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.routine_detail.routine_list.data.viewmodel.RoutineListViewModel
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListInfoState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListScreenState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.rememberRoutineListScreen
import com.gradation.lift.feature.routine_detail.routine_list.ui.RoutineListScreen

@Composable
internal fun RoutineListRoute(
    modifier: Modifier = Modifier,
    navigateRoutineDetailGraphToHomeGraph: () -> Unit,
    navigateRoutineDetailGraphToCreateRoutineGraph: () -> Unit,
    navigateRoutineListToRoutineInRoutineDetailGraph: (Int) -> Unit,
    viewModel: RoutineListViewModel = hiltViewModel(),
    routineListScreenState: RoutineListScreenState = rememberRoutineListScreen()
) {

    val sortFilterState: SortFilterState = viewModel.sortFilterState
    val routineListInfoState: RoutineListInfoState = viewModel.routineListInfoState
    val routineSetRoutineList: RoutineListUiState by viewModel.routineSetRoutineList.collectAsStateWithLifecycle()


    val labelFilterType: LabelFilterType by sortFilterState.labelFilterType.collectAsStateWithLifecycle()
    val weekdayFilterType: WeekdayFilterType by sortFilterState.weekdayFilterType.collectAsStateWithLifecycle()
    val searchFilterText: String by sortFilterState.searchFilterText.collectAsStateWithLifecycle()
    val sortType: SortType by sortFilterState.sortType.collectAsStateWithLifecycle()


    BackHandler(onBack = navigateRoutineDetailGraphToHomeGraph)

    RoutineListScreen(
        modifier,
        routineListScreenState,
        routineListInfoState,
        routineSetRoutineList,
        sortFilterState,
        labelFilterType,
        weekdayFilterType,
        searchFilterText,
        sortType,
        navigateRoutineDetailGraphToHomeGraph,
        navigateRoutineDetailGraphToCreateRoutineGraph,
        navigateRoutineListToRoutineInRoutineDetailGraph
    )

}