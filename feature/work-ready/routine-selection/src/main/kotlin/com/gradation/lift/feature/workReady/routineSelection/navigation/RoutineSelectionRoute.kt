package com.gradation.lift.feature.workReady.routineSelection.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.workReady.common.WorkReadySharedViewModel
import com.gradation.lift.feature.workReady.routineSelection.data.model.LabelFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.model.SortType
import com.gradation.lift.feature.workReady.routineSelection.data.model.WeekdayFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListInfoState
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListUiState
import com.gradation.lift.feature.workReady.routineSelection.data.state.SortFilterState
import com.gradation.lift.feature.workReady.routineSelection.data.state.rememberRoutineListScreen
import com.gradation.lift.feature.workReady.routineSelection.data.viewmodel.RoutineSelectionViewModel
import com.gradation.lift.feature.workReady.routineSelection.ui.RoutineSelectionScreen
import com.gradation.lift.feature.workReady.routineSelection.ui.component.bottomsheet.LabelFilterBottomSheet
import com.gradation.lift.feature.workReady.routineSelection.ui.component.bottomsheet.SortBottomSheet
import com.gradation.lift.feature.workReady.routineSelection.ui.component.bottomsheet.WeekdayFilterBottomSheet
import com.gradation.lift.navigation.Route

@Composable
internal fun RoutineSelectionRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    popBackStack: () -> Unit,
    navigateRoutineSelectionToReadyInWorkReadyGraph: () -> Unit,
    viewModel: RoutineSelectionViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: WorkReadySharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.WORK_READY_GRAPH_NAME) }),
    routineListScreenState: RoutineListScreenState = rememberRoutineListScreen(),
) {


    val sortFilterState: SortFilterState = viewModel.sortFilterState
    val routineListInfoState: RoutineListInfoState = viewModel.routineListInfoState
    val routineSetRoutineList: RoutineListUiState by viewModel.routineSetRoutineList.collectAsStateWithLifecycle()


    val labelFilterType: LabelFilterType by sortFilterState.labelFilterType.collectAsStateWithLifecycle()
    val weekdayFilterType: WeekdayFilterType by sortFilterState.weekdayFilterType.collectAsStateWithLifecycle()
    val searchFilterText: String by sortFilterState.searchFilterText.collectAsStateWithLifecycle()
    val sortType: SortType by sortFilterState.sortType.collectAsStateWithLifecycle()

    val updateRoutineSetIdSet: (Set<Int>) -> Unit = sharedViewModel.updateRoutineSetIdSet
    BackHandler(onBack = popBackStack)


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
        updateRoutineSetIdSet,
        popBackStack,
        navigateRoutineSelectionToReadyInWorkReadyGraph,
    )

}