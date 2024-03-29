package com.gradation.lift.feature.routineDetail.routineList.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.routineDetail.routineList.data.viewmodel.RoutineListViewModel
import com.gradation.lift.feature.routineDetail.routineList.data.model.LabelFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.model.SortType
import com.gradation.lift.feature.routineDetail.routineList.data.model.WeekdayFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListUiState
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListInfoState
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListScreenState
import com.gradation.lift.feature.routineDetail.routineList.data.state.SortFilterState
import com.gradation.lift.feature.routineDetail.routineList.data.state.rememberRoutineListScreen
import com.gradation.lift.feature.routineDetail.routineList.ui.RoutineListScreen
import com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet.LabelFilterBottomSheet
import com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet.SortBottomSheet
import com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet.WeekdayFilterBottomSheet

@Composable
internal fun RoutineListRoute(
    modifier: Modifier = Modifier,
    navigateRoutineDetailGraphToHomeGraph: () -> Unit,
    navigateRoutineDetailGraphToCreateRoutineGraph: () -> Unit,
    navigateRoutineListToRoutineInRoutineDetailGraph: (Int) -> Unit,
    viewModel: RoutineListViewModel = hiltViewModel(),
    routineListScreenState: RoutineListScreenState = rememberRoutineListScreen(),
) {

    val sortFilterState: SortFilterState = viewModel.sortFilterState
    val routineListInfoState: RoutineListInfoState = viewModel.routineListInfoState
    val routineSetRoutineList: RoutineListUiState by viewModel.routineSetRoutineList.collectAsStateWithLifecycle()


    val labelFilterType: LabelFilterType by sortFilterState.labelFilterType.collectAsStateWithLifecycle()
    val weekdayFilterType: WeekdayFilterType by sortFilterState.weekdayFilterType.collectAsStateWithLifecycle()
    val searchFilterText: String by sortFilterState.searchFilterText.collectAsStateWithLifecycle()
    val sortType: SortType by sortFilterState.sortType.collectAsStateWithLifecycle()


    BackHandler(onBack = navigateRoutineDetailGraphToHomeGraph)

    AnimatedVisibility(
        routineListScreenState.sortTypeBottomSheetView,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut()
    ) {
        SortBottomSheet(modifier, routineListScreenState, sortFilterState, sortType)
    }
    AnimatedVisibility(
        routineListScreenState.weekdayFilterTypeBottomSheetView,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut()
    ) {
        WeekdayFilterBottomSheet(
            modifier,
            routineListScreenState,
            sortFilterState,
            weekdayFilterType
        )
    }
    AnimatedVisibility(
        routineListScreenState.labelFilterTypeBottomSheetView,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut()
    ) {
        LabelFilterBottomSheet(
            modifier,
            routineListScreenState,
            sortFilterState,
            labelFilterType
        )
    }

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